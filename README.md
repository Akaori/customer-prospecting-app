# Customer Prospecting App

![test workflow](https://github.com/Akaori/customer-prospecting-app/actions/workflows/test.yaml/badge.svg)

## Como entrar no Swagger

- Acessar a seguinte URL:

```
http://localhost:8080/swagger-ui/index.html#/
```

![swagger.png](images/swagger.png)

- Testar rotas

- Será necessário colocar as credencias para executar os endpoints:

  - user: `user`
  - password: `user`

> Exemplo de post request: (se o cadastro ou atualização do cadastro não obedecer as regras de cada campo, será mostrado um erro correspondente)
![post_request.png](images/post_request.png)

## Como entrar no H2

- Acessar a seguinte URL:

```
http://localhost:8080/h2-console
```

- Preencher os campos de acordo com a imagem abaixo:

![access_h2.png](images/access_h2.png)

- Clicar em `Connect`


## Testes automatizados

- Testes unitários para os serviços
- CI com [Github Actions](https://github.com/Akaori/customer-prospecting-app/actions)