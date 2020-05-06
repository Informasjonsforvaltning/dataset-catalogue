# Dataset Catalogue (API) generated from a-backend-service

Base image: [frolvlad/alpine-oraclejdk8:slim](https://hub.docker.com/r/frolvlad/alpine-oraclejdk8/)
Source: [Dockerfile](https://github.com/Informasjonsforvaltning/dataset-catalogue/blob/develop/src/main/docker/Dockerfile)

##  Overview
Registration api provides a REST service for creating, updating and storing DCAT-AP-NO 1.1. dataset descriptions.  

## Technologies/frameworks
* Java
* Spring Boot v1.5.9
* Apache Jena v.3.3.0

## API
Registration api exposes several endpoints for CRUD on catalogs and dataset descriptions.

* ```GET /catalogs```
    * Returns all catalogs user is authorized for as JSON.
* ```POST /catalogs```
    * Create a catalog, catalog as JSON in payload.
    * Returns a catalog as JSON.
* ```GET /catalogs/{id}```
    * Returns a catalog as JSON.
    * Parameter
        - ```id``` : Get catalog with this id.
* ```DELETE /catalogs/{id}```
    * Delete a catalog.
    * Return HTTP.OK or HTTP.NOT_FOUND.
    * Parameter
        - ```id``` : Delete catalog with this id.
* ```PUT /catalogs/{id}```
    * Modify a catalog, catalog as JSON in payload.
    * Parameter
        - ```id``` : Modify catalog with this id.
* ```GET /catalogs/{catalogId}/datasets/{id}```
    * Returns a dataset description as JSON.
    * Parameters
        - catalogId: Id of the catalog.
        - ```id``` : Get a dataset with this id.
* ```GET /catalogs/{catalogId}/datasets```
    * Returns all dataset descriptions in a catalog.
    * Parameter
        - ```catalogId``` : Id of the catalog.
* ```POST /catalogs/{catalogId}/datasets```
    * Create a dataset description, dataset description as JSON in payload. 
    * Returns the created dataset as JSON.
* ```PATCH /catalogs/{catalogId}/datasets/{id}```
    * Modify a dataset description, dataset description as JSON in payload.
    * Returns the modified dataset as JSON. 
    * Parameters
        - ```catalogId``` : Id of the catalog.
        - ```id``` : Modify dataset description with this id.
* ```DELETE /catalogs/{catalogId}/datasets``` 
    * Delete a dataset description.
    * Returns HTTP.OK or HTTP.NOT_FOUND.
    * Parameters
        - ```catalogId``` : Id of the catalog.
        - ```id``` : Delete dataset description with this id.
* ```GET /subjects?uri={uri}```
    * Returns 1...n subject(s) as JSON.
    * Parameter
        - ```uri``` : Uri to which subject(s) in DCAT format to get.

## Dependencies
* reference-data
* elasticsearch

## Run locally in IDEA
Start local instances of dependencies
```
% docker-compose up -d
```
-d enables "detached mode"

Add `-Dspring.profiles.active=develop` as a VM option in the Run/Debug Configuration

Run (Shift+F10) or debug (Shift+F9) the application

## Get an admin jwt:
```
% curl localhost:8084/jwt/admin -o jwt.txt
```

## Get a write token for an organization jwt:
```
% curl localhost:8084/jwt/write -o jwt.txt
```

## Helpful commands

Create catalogue, replace <token> with generated jwt
```
curl localhost:8114/catalogs -d '{"id":"910244132"}' -H 'content-type:application/json' -H 'Authorization: Bearer <token>' 
```

Create dataset, replace <token> with generated jwt
```
curl localhost:8114/catalogs/910244132/datasets -d '{}' -H 'content-type:application/json' -H 'Authorization: Bearer <token>' 
```

## Troubleshooting:
### ClassNotFoundException: javax.xml.bind.JAXBException
You are running the wrong java version, switch to java 8