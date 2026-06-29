param keyVaultName string
param acrName string
param storageAccountName string
param uamiId string
param uamiPrincipalId string

var kvSecretsUserRoleId = '4633458b-17de-408a-b874-0445c86b69e6'
var acrPullRoleId = '7f951dda-4ed3-4680-a7ca-43fe172d538d'
var stBlobDataContributorRoleId = 'ba92f5b4-2d11-453d-a403-e96b0029c9fe'

// Recreating the resources as existing to assign roles to the UAMI
// This is because resources created in other modules cannot be referenced directly for role assignments in this module
resource keyVault 'Microsoft.KeyVault/vaults@2023-07-01' existing = {
  name: keyVaultName
}

resource acr 'Microsoft.ContainerRegistry/registries@2024-11-01-preview' existing = {
  name: acrName
}

resource storageAccount 'Microsoft.Storage/storageAccounts@2023-05-01' existing = {
  name: storageAccountName
}

// Allow the UAMI to read secrets from the Key Vault by assigning the Key Vault Secrets User role
resource keyVaultRoleAssignment 'Microsoft.Authorization/roleAssignments@2022-04-01' = {
  name: guid(keyVault.id, uamiId, 'KeyVaultSecretsUser')
  scope: keyVault
  properties: {
    principalId: uamiPrincipalId
    roleDefinitionId: subscriptionResourceId('Microsoft.Authorization/roleDefinitions', kvSecretsUserRoleId)
    principalType: 'ServicePrincipal'
  }
}

// Assign the AcrPull role to the user-assigned managed identity for the ACR
// This will allow the AKS cluster to pull images from the ACR using the managed identity
resource acrPullRoleAssignment 'Microsoft.Authorization/roleAssignments@2022-04-01' = {
  name: guid(acr.id, uamiId, 'AcrPull')
  scope: acr
  properties: {
    principalId: uamiPrincipalId
    roleDefinitionId: subscriptionResourceId('Microsoft.Authorization/roleDefinitions', acrPullRoleId)
    principalType: 'ServicePrincipal'
  }
}

// Assign the Storage Blob Data Contributor to the UAMI
// This will allow the AKS cluster to access the storage account using the managed identity
resource storageBlobRole 'Microsoft.Authorization/roleAssignments@2022-04-01' = {
  name: guid(storageAccount.id, uamiId, 'BlobContributor')
  scope: storageAccount
  properties: {
    principalId: uamiPrincipalId
    roleDefinitionId: subscriptionResourceId('Microsoft.Authorization/roleDefinitions', stBlobDataContributorRoleId)
    principalType: 'ServicePrincipal'
  }
}
