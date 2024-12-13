

---

# 🛒 API de E-commerce com Spring Boot  

<img src="https://img.shields.io/badge/SpringBoot-v3.1.2-green" alt="Spring Boot version">  
<img src="https://img.shields.io/badge/Status-Em%20Desenvolvimento-yellow" alt="Status">  
<img src="https://img.shields.io/badge/License-MIT-blue" alt="License">  

## 📝 Descrição  
Este repositório contém uma API desenvolvida em **Java com Spring Boot**, que simula o funcionamento de um sistema de e-commerce. A API permite o gerenciamento de **clientes (customers)**, **pedidos (orders)** e **produtos (products)**, abrangendo operações de criação, leitura, atualização e exclusão (CRUD) para cada entidade.

---

## 🚀 Funcionalidades  

- Gerenciamento de clientes (Customer):  
  - Adicionar, listar, atualizar e excluir clientes.  
- Gerenciamento de produtos (Product):  
  - Adicionar, listar, atualizar e excluir produtos.  
- Gerenciamento de pedidos (Order):  
  - Criar pedidos vinculando clientes e produtos.  
  - Calcular automaticamente o valor total do pedido.  

---

## 🗂️ Estrutura do Projeto  

```plaintext
.
└── src
    ├── main
    │   ├── java
    │   │   └── com.example.ecommerce
    │   │       ├── customer
    │   │       │   ├── Customer.java            # Entidade Customer
    │   │       │   ├── CustomerController.java  # Controller Customer
    │   │       │   ├── CustomerRepository.java  # Repository Customer
    │   │       │   └── CustomerService.java     # Service Customer
    │   │       ├── order
    │   │       │   ├── Order.java               # Entidade Order
    │   │       │   ├── OrderController.java     # Controller Order
    │   │       │   ├── OrderRepository.java     # Repository Order
    │   │       │   └── OrderService.java        # Service Order
    │   │       ├── product
    │   │       │   ├── Product.java             # Entidade Product
    │   │       │   ├── ProductController.java   # Controller Product
    │   │       │   ├── ProductRepository.java   # Repository Product
    │   │       │   └── ProductService.java      # Service Product
    │   │       └── EcommerceApplication.java    # Classe principal da aplicação
    │   └── resources
    │       ├── application.properties           # Configurações da aplicação
    │       └── data.sql                         # Dados de exemplo (se aplicável)
    └── test
        └── java
            └── com.example.ecommerce            # Testes unitários e de integração
```

---

## 🛠️ Tecnologias Utilizadas  

- **Java 17** ☕  
- **Spring Boot** 🌱  
- **Spring Data JPA** 🗄️  
- **H2 Database** (em memória, para testes) 🛢️  
- **MySQL** (ou PostgreSQL, para produção) 🛢️  
- **Maven** ⚙️  

---

## 📖 Documentação  

### 🧑‍🤝‍🧑 Entidade Customer  
**Atributos**:  
- `id`: Identificador único.  
- `name`: Nome do cliente.  
- `email`: Email do cliente.  

### 📦 Entidade Product  
**Atributos**:  
- `id`: Identificador único.  
- `name`: Nome do produto.  
- `price`: Preço do produto.  

### 📋 Entidade Order  
**Atributos**:  
- `id`: Identificador único.  
- `customerId`: Identificador do cliente que fez o pedido.  
- `products`: Lista de produtos no pedido.  
- `totalValue`: Valor total do pedido, calculado automaticamente.  

---

## 🖼️ Exemplo de Uso  

### 📌 Cadastro de Produto  
**Request (POST)**:  
`/api/products`  
```json
{
  "name": "Notebook",
  "price": 2500.0
}
```

### 📌 Criação de Pedido  
**Request (POST)**:  
`/api/orders`  
```json
{
  "customerId": 1,
  "products": [1, 2]
}
```

**Response (JSON)**:  
```json
{
  "id": 1,
  "customerId": 1,
  "products": [
    {
      "id": 1,
      "name": "Notebook",
      "price": 2500.0
    },
    {
      "id": 2,
      "name": "Mouse",
      "price": 150.0
    }
  ],
  "totalValue": 2650.0
}
```

---

## 🧪 Testes  

- Testes unitários para os métodos de cada **Service**.  
- Testes de integração para os endpoints dos **Controllers**.  

---

## 📄 Como Executar  

1. Clone o repositório:  
   ```bash
   git clone https://github.com/seuusuario/ecommerce-api.git
   cd ecommerce-api
   ```  
2. Configure o banco de dados em `application.properties`.  
3. Execute o projeto:  
   ```bash
   mvn spring-boot:run
   ```  
4. Acesse a API em:  
   [http://localhost:8080](http://localhost:8080)  

---

