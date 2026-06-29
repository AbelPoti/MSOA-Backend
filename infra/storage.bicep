param rgLocation string

@description('Storage account name (must be globally unique, 3-24 lower-case letters/numbers')
param storageAccountName string

resource storageAccount 'Microsoft.Storage/storageAccounts@2023-05-01' = {
  name: storageAccountName
  location: rgLocation
  sku: {
    name: 'Standard_LRS'
  }
  kind: 'StorageV2'
  properties: {
    accessTier: 'Hot'
    allowBlobPublicAccess: false
  }
}

output storageAccountName string = storageAccount.name
