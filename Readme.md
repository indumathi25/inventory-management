INVENTORY MANAGEMENT SERVICE
---

This micro-service is used to upload the articles and products from a JSON file.

Package structures:
---
1. src/main/java:

     com.warehouse.inventorymanagement.api.controller:
       This package for all api level cobtrollers present
       
     com.warehouse.inventorymanagement.messaging.controller:
       This package is for all kafka messaging consumer controllers.
       
     com.warehouse.inventorymanagement.data:
       This package contains all the data model for databases
       
     com.warehouse.inventorymanagement.model:
       This package contains all the API request model
       
     com.warehouse.inventorymanagement.repositories:
       This package contains all the database repo to save the data
       
     com.warehouse.inventorymanagement.service:   
       This package contains all the service level/business logic level interfaces
       
     com.warehouse.inventorymanagement.service.impl:     
        This package contains all the service level implementation
        
     com.warehouse.inventorymanagement.utils:
        This package contains all the helper utils, Kindly note that this one has to be moved to common libs to reuse this in multiple microservices
        
      com.productcatalogservice.data:
         This package is kept here since kafka connot deserialize the different package other than serialized one, this also has to move to commonlib or schema registry side
         
         
         
2. Entry point:
   The Entry point for this spring application is InventoryManagementApplication
   
   
API's:
---

1. curl --location --request POST 'http://localhost:8082/inventory/api/v1/articles' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--form 'file=@"/Users/velan/Desktop/inventory.json"'

Description:

  This api is used to upload the inventory.json file from the inventory management service
  
Response Body:

```
{
    "message": "Artcles loaded successfully",
    "data": [
        {
            "articlesAdded": true
        }
    ],
    "code": 200
}
```


2. curl --location --request POST 'http://localhost:8082/inventory/api/v1/products' \
--header 'Content-Type: application/json' \
--form 'file=@"/Users/velan/Desktop/products.json"'

Description:

  This api is used to upload the product.json file from the inventory management service
  
Response Body:

```
{
    "message": "Products loaded successfully",
    "data": [
        {
            "articlesAdded": true
        }
    ],
    "code": 200
}
```

Features to improve:
---
Need to add proper error handling



Note:
---
This code is not production grade code.
        
