param rgLocation string

@description('Key vault name (must be globally unique with 3-24 characters)')
param keyVaultName string

resource keyVault 'Microsoft.KeyVault/vaults@2023-07-01' = {
  name: keyVaultName
  location: rgLocation
  properties: {
    tenantId: subscription().tenantId
    sku: {
      family: 'A'
      name: 'standard'
    }
    enableRbacAuthorization: true
    enableSoftDelete: false
    enabledForTemplateDeployment: true
    publicNetworkAccess: 'Enabled'
  }
}

output keyVaultName string = keyVault.name
