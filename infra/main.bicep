param rgLocation string = resourceGroup().location

@description('User-assigned managed identity name')
param uamiName string

@description('Storage account name (must be globally unique, 3-24 lower-case letters/numbers')
param storageAccountName string

@description('Key vault name (must be globally unique with 3-24 characters)')
param keyVaultName string

@description('AKS cluster name')
param aksName string

@description('ACR name (5-50 alphanumeric)')
param acrName string

module identity 'identity.bicep' = {
  name: 'identity'
  params: {
    rgLocation: rgLocation
    uamiName: uamiName
  }
}

module storage 'storage.bicep' = {
  name: 'storage'
  params: {
    rgLocation: rgLocation
    storageAccountName: storageAccountName
  }
}

module keyVault 'keyvault.bicep' = {
  name: 'keyVault'
  params: {
    rgLocation: rgLocation
    keyVaultName: keyVaultName
  }
}

module acr 'acr.bicep' = {
  name: 'acr'
  params: {
    rgLocation: rgLocation
    acrName: acrName
  }
}

module aks 'aks.bicep' = {
  name: 'aks'
  params: {
    rgLocation: rgLocation
    aksName: aksName
    uamiId: identity.outputs.uamiId
  }
}

module roles 'roles.bicep' = {
  name: 'roles'
  params: {
    uamiId: identity.outputs.uamiId
    uamiPrincipalId: identity.outputs.uamiPrincipalId
    keyVaultName: keyVault.outputs.keyVaultName
    acrName: acr.outputs.acrName
    storageAccountName: storage.outputs.storageAccountName
  }
}
