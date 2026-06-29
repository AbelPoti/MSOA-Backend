param rgLocation string

@description('User-assigned managed identity name')
param uamiName string

resource uami 'Microsoft.ManagedIdentity/userAssignedIdentities@2024-11-30' = {
  name: uamiName
  location: rgLocation
}

output uami object = uami
output uamiId string = uami.id
output uamiPrincipalId string = uami.properties.principalId
