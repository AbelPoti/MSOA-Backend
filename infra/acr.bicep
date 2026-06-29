param rgLocation string

@description('ACR name (5-50 alphanumeric)')
param acrName string

resource acr 'Microsoft.ContainerRegistry/registries@2024-11-01-preview' = {
  name: acrName
  location: rgLocation
  sku: {
    name: 'Basic'
  }
  properties: {
    adminUserEnabled: false
  }
}

output acrName string = acr.name
