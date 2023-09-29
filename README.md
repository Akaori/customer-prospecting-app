# Customer Prospecting App

![test workflow](https://github.com/Akaori/customer-prospecting-app/actions/workflows/test.yaml/badge.svg)


# 🔍 Sobre

Aplicação full-stack (front-end e back-end) para manter um pré-cadastro de clientes (prospect) para possibilitar uma futura oferta de produtos e serviços a esses clientes.

Além disso, possui um sistema de fila de atendimento ao prospects, para que cada cliente possa ser analisado de forma sequencial pelos gestores comerciais.

Para ver o projeto, clique [aqui](https://github.com/users/Akaori/projects/3)

# 💻 Instruções para rodar aplicação com o Docker

> É necessário ter o docker e docker-compose instalados. Para instalar no Ubuntu, seguir o seguinte tutorial: 
> https://www.digitalocean.com/community/tutorials/how-to-install-and-use-docker-compose-on-ubuntu-20-04-pt


> Se não tiver o Docker instalado, abaixo há instruções para rodar a aplicação sem o Docker.

### 1. Clonar repositório

```
git clone https://github.com/Akaori/customer-prospecting-app.git

```

### 2. Mudar para diretório do repositório clonado

```
cd customer-prospecting-app
```

### 3. Subir serviços com o docker-compose

```
docker-compose up
```

Os serviços de backend e frontend subirão automaticamente.

# 💻 Instruções para rodar aplicação sem o Docker

### 1. Rodar o backend

[Instruções para rodar backend sem o Docker](https://github.com/Akaori/customer-prospecting-app/tree/main/backend)

### 2. Rodar o frontend

[Instruções para rodar frontend sem o Docker](https://github.com/Akaori/customer-prospecting-app/tree/main/frontend)

# 💡 Instruções de acesso aos serviços

### 1. Como entrar no Swagger

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


### 2. Como entrar no frontend

Acessar em:

```
http://localhost:3000/
```

![frontend.png](images/frontend.png)


# 🌐 Overview do Projeto


## Backend

### 1. Estrutura de tabelas

- IndividualCustomer: Pessoa Física
- LegalEntityCustomer: Pessoa Jurídica

```mermaid
classDiagram
class IndividualCustomer {
+Long id
+String name
+String mcc
+String cpf
+String email
}

class LegalEntityCustomer {
+Long id
+String corporateName
+String cnpj
+String mcc
+String contactName
+String contactCpf
+String email
}
```

### 2. Validação dos campos no backend

![validations.png](images/validations.png)

### 3. Endpoints 

Para cada tipo de cliente (pessoa jurídica e pessoa física), há os seguintes endpoints:
- criação de pré-cadastros de clientes
- alteração de pré-cadastros de clientes
- exclusão de pré-cadastros de clientes
- consulta por id de pré-cadastros de clientes
- consulta de todos os pré-cadastros de clientes
- retirada do próximo cliente da fila de atendimento

![endpoints.png](images/endpoints.png)

### 4. Fila de atendimento

Implementado na linguagem java uma estrutura de dados para uma fila, onde seja possível acrescentar e retirar clientes na fila no modelo FIFO (First In, First Out).


### 5. Testes automatizados

- [Testes unitários para todos os serviços do backend](https://github.com/Akaori/customer-prospecting-app/tree/main/backend/src/test/java/com/challenge/customerprospecting/service)
- CI com [Github Actions](https://github.com/Akaori/customer-prospecting-app/actions)

### 5. Segurança

Para a aplicação backend, foi configurado o [Basic Auth e CORS](https://github.com/Akaori/customer-prospecting-app/blob/main/backend/src/main/java/com/challenge/customerprospecting/config/SecurityConfig.java).


## Frontend

### Dark theme e Light theme

![dark.png](images/dark.png)
![light.png](images/light.png)

### 1. Tela de criação

- Validação dos campos
![create_form.png](images/create_form.png)

- Alerta de sucesso
![sucess.png](images/sucess.png)

- Alerta de erro
![failure.png](images/failure.png)


### 2. Tela de consulta, alteração e exclusão

![list_customers.png](images/list_customers.png)

### 3. Tela de recuperar prospect da fila

Quando há clientes na fila:

![prospect.png](images/prospect.png)

Quando não há clientes na fila:

![no_customers.png](images/no_customers.png)

### 4.Tela para página não encontrada 404

![img.png](images/404.png)

### 5. Acessibilidade WCAG 2 no Frontend

É possível ver alguns pontos da acessbilidade do site no seguinte pull request: https://github.com/Akaori/customer-prospecting-app/pull/44

## 🖥️ Tecnologias

- Backend:

<div align="center">
	<code><img width="50" src="https://user-images.githubusercontent.com/25181517/192108890-200809d1-439c-4e23-90d3-b090cf9a4eea.png" alt="InteliJ" title="InteliJ"/></code>
	<code><img width="50" src="https://user-images.githubusercontent.com/25181517/186711335-a3729606-5a78-4496-9a36-06efcc74f800.png" alt="Swagger" title="Swagger"/></code>
	<code><img width="50" src="https://user-images.githubusercontent.com/25181517/117201156-9a724800-adec-11eb-9a9d-3cd0f67da4bc.png" alt="Java" title="Java"/></code>
	<code><img width="50" src="https://user-images.githubusercontent.com/25181517/183891303-41f257f8-6b3d-487c-aa56-c497b880d0fb.png" alt="Spring Boot" title="Spring Boot"/></code>
	<code><img width="50" src="https://user-images.githubusercontent.com/25181517/117533873-484d4480-afef-11eb-9fad-67c8605e3592.png" alt="JUnit" title="JUnit"/></code>
	<code><img width="50" src="https://user-images.githubusercontent.com/25181517/183892181-ad32b69e-3603-418c-b8e7-99e976c2a784.png" alt="mocikto" title="mocikto"/></code>
	<code><img width="50" src="https://user-images.githubusercontent.com/25181517/190229463-87fa862f-ccf0-48da-8023-940d287df610.png" alt="Lombok" title="Lombok"/></code>
	<code><img width="50" src="https://user-images.githubusercontent.com/25181517/117207330-263ba280-adf4-11eb-9b97-0ac5b40bc3be.png" alt="Docker" title="Docker"/></code>
</div>

- Frontend:

<div align="center">
	<code><img width="50" src="https://user-images.githubusercontent.com/25181517/192108891-d86b6220-e232-423a-bf5f-90903e6887c3.png" alt="Visual Studio Code" title="Visual Studio Code"/></code>
	<code><img width="50" src="https://user-images.githubusercontent.com/25181517/192158954-f88b5814-d510-4564-b285-dff7d6400dad.png" alt="HTML" title="HTML"/></code>
	<code><img width="50" src="https://user-images.githubusercontent.com/25181517/183898674-75a4a1b1-f960-4ea9-abcb-637170a00a75.png" alt="CSS" title="CSS"/></code>
	<code><img width="50" src="https://user-images.githubusercontent.com/25181517/189716630-fe6c084c-6c66-43af-aa49-64c8aea4a5c2.png" alt="Material UI" title="Material UI"/></code>
	<code><img width="50" src="https://user-images.githubusercontent.com/25181517/117447155-6a868a00-af3d-11eb-9cfe-245df15c9f3f.png" alt="JavaScript" title="JavaScript"/></code>
	<code><img width="50" src="https://user-images.githubusercontent.com/25181517/183897015-94a058a6-b86e-4e42-a37f-bf92061753e5.png" alt="React" title="React"/></code>
	<code><img width="50" src="https://user-images.githubusercontent.com/25181517/121401671-49102800-c959-11eb-9f6f-74d49a5e1774.png" alt="npm" title="npm"/></code>
	<code><img width="50" src="https://user-images.githubusercontent.com/25181517/183568594-85e280a7-0d7e-4d1a-9028-c8c2209e073c.png" alt="Node.js" title="Node.js"/></code>
	<code><img width="50" src="https://user-images.githubusercontent.com/25181517/117207330-263ba280-adf4-11eb-9b97-0ac5b40bc3be.png" alt="Docker" title="Docker"/></code>
</div>



