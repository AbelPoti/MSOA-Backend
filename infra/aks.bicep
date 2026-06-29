param rgLocation string

@description('AKS cluster name')
param aksName string

@description('User-assigned managed identity ID')
param uamiId string

resource aks 'Microsoft.ContainerService/managedClusters@2024-10-01' = {
  name: aksName
  location: rgLocation

  identity: {
    type: 'UserAssigned'
    userAssignedIdentities: {
      '${uamiId}': {}
    }
  }

  properties: {
    dnsPrefix: '${aksName}-dns'

    agentPoolProfiles: [
      {
        name: 'system'
        mode: 'System'
        count: 1
        vmSize: 'Standard_B2s'
        osType: 'Linux'
        type: 'VirtualMachineScaleSets'
      }
    ]

    networkProfile: {
      networkPlugin: 'azure'
      loadBalancerSku: 'standard'
    }
  }
}
