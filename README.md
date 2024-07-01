# Marketing - Hexagonal API
Marketing API 

Port to test 8080

Import this API to Postman:

## To create product
curl --location 'http://localhost:8080/products' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=69606D58D76A9D0E99DE3B148D9D5315' \
--data '{      
    "price": 299.99,
    "description": "Cadeira ergonômica para escritório.",
    "category": {
      "id": 1,
      "name": "Móveis de Escritório"
    },
    "available": true,
    "colour": "Preto"
 
}'

## To update product
curl --location --request PUT 'http://localhost:8080/products/1' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=69606D58D76A9D0E99DE3B148D9D5315' \
--data '{
    "id": 1,
    "price": 299.30,
    "description": "Cadeira ergonômica para escritório.",
    "category": {
        "id": 1,
        "name": "Móveis de Escritório"
    },
    "available": true,
    "colour": "Preto"
}'



## To get product
curl --location 'http://localhost:8080/products/1' \
--header 'Cookie: JSESSIONID=69606D58D76A9D0E99DE3B148D9D5315' \
--data ''

## To get all products
curl --location 'http://localhost:8080/products' \
--header 'Cookie: JSESSIONID=69606D58D76A9D0E99DE3B148D9D5315' \
--data ''

## To get products sorted by price
curl --location 'http://localhost:8080/products/sortedByPrice' \
--header 'Cookie: JSESSIONID=69606D58D76A9D0E99DE3B148D9D5315' \
--data ''

## To login
curl --location 'http://localhost:8080/login' \
--header 'Cookie: JSESSIONID=69606D58D76A9D0E99DE3B148D9D5315'

## To delete product
curl --location --request DELETE 'http://localhost:8080/products/delete/1' \
--header 'Cookie: JSESSIONID=69606D58D76A9D0E99DE3B148D9D5315'

