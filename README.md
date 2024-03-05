# Partner Credits

## JDK VERSION
```sh
Java 21
```

## Run Application
```sh
$ mvn spring-boot:run
```

## Run Test
```sh
$ mvn test
```

## Endpoints

Simulate credit
```sh
curl --location 'http://localhost:8080/credits/simulate?currency=ARS&amount=1000000&installments=12'
```

Validate data
```sh
curl --location 'http://localhost:8080/credits/validate-data' \
--header 'Content-Type: application/json' \
--data-raw '{
    "mail":"test@pedidosya.com",
    "phone":"+541122406680",
    "contract":"02a7a454-ef9d-46b6-b10e-144c26a16ff2"
}'
```

Confirm credit
```sh
curl --location 'http://localhost:8080/credits/confirm' \
--header 'Content-Type: application/json' \
--data-raw '{
    "uuid": "795b5235-49a8-4082-a78b-901b8661e939",
    "currency": "ARS",
    "amount": 1000000,
    "installments": 12,
    "total_amount": 1500000,
    "mail": "test@pedidosya.com",
    "installment_plan_details": [
        {
            "number": 1,
            "amount": 189049.16,
            "interest": 122513.42,
            "rate": 35.43
        },
        {
            "number": 2,
            "amount": 189049.16,
            "interest": 122513.42,
            "rate": 35.43
        },
{
            "number": 3,
            "amount": 189049.16,
            "interest": 122513.42,
            "rate": 35.43
        },
        {
            "number": 4,
            "amount": 189049.16,
            "interest": 122513.42,
            "rate": 35.43
        },
        {
            "number": 5,
            "amount": 189049.16,
            "interest": 122513.42,
            "rate": 35.43
        },
        {
            "number": 6,
            "amount": 189049.16,
            "interest": 122513.42,
            "rate": 35.43
        },
        {
            "number": 7,
            "amount": 189049.16,
            "interest": 122513.42,
            "rate": 35.43
        },
        {
            "number": 8,
            "amount": 189049.16,
            "interest": 122513.42,
            "rate": 35.43
        },
        {
            "number": 9,
            "amount": 189049.16,
            "interest": 122513.42,
            "rate": 35.43
        },
        {
            "number": 10,
            "amount": 189049.16,
            "interest": 122513.42,
            "rate": 35.43
        },
        {
            "number": 11,
            "amount": 189049.16,
            "interest": 122513.42,
            "rate": 35.43
        },
        {
            "number": 12,
            "amount": 189049.16,
            "interest": 122513.42,
            "rate": 35.43
        }
    ]
}'
```