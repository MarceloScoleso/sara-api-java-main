# 🌐 Projeto SARA - Sistema de Alerta e Resposta Automatizada

## 🧠 Resumo

O Projeto **SARA (Sistema de Alerta e Resposta Automatizada)** é uma solução tecnológica desenvolvida para mitigar os impactos de eventos extremos — como enchentes, deslizamentos, secas e outros desastres naturais — sobre a população. Através de sensores conectados, alertas inteligentes e notificações automatizadas, o sistema integra tecnologia e inovação para monitorar e responder rapidamente a situações críticas, promovendo segurança pública e bem-estar social.

Esta API REST, construída com **Spring Boot**, gerencia os dados e regras de negócio da aplicação, oferecendo suporte a autenticação segura com JWT, cadastro de usuários, gerenciamento de sensores, leitura de dados, emissão de alertas e envio de notificações.

---

## 🚀 Tecnologias Utilizadas

- Java 21
- Spring Boot 3.x  
- Spring Data JPA  
- Spring Security com JWT  
- Bean Validation (Jakarta)  
- Oracle Database  
- Swagger / OpenAPI 3  
- Lombok  
- MapStruct  

---

## 📦 Requisitos

- Java 21 ou superior  
- Maven 3.8 ou superior  
- Banco Oracle Database (local ou remoto)  
- IDE de sua preferência (IntelliJ, VS Code, Eclipse, etc)  

---

## ⚙️ Configuração e Execução

### 1. Clone o repositório


git clone https://github.com/seu-usuario/projeto-sara.git
cd projeto-sara

---


---

## ☁️ Como rodar o Projeto SARA na Nuvem

Esta seção traz um guia básico para implantar o projeto SARA em ambientes de nuvem usando containers Docker, facilitando a escalabilidade, portabilidade e gerenciamento da aplicação.
---
### 1. Pré-requisitos para deploy em nuvem

- Conta em um provedor de nuvem (AWS, Azure, Google Cloud, etc)

- Ambiente com Docker e Docker Compose instalados

- Configuração de firewall para liberar portas da aplicação (8080) e do banco (1521)

- Registro de imagem Docker (Docker Hub, AWS ECR, Azure Container Registry, Google Container Registry)

### 2. Gerar a imagem Docker da aplicação

No diretório do projeto, construa sua imagem:
---
docker build -t seu_usuario/projeto-sara-api-java:latest .
Faça login no Docker Hub (ou registro escolhido):

docker login

Envie a imagem para o repositório remoto:

docker push seu_usuario/projeto-sara-api-java:latest

### 3. Subir os containers em nuvem

Acesse sua VM ou serviço gerenciado com Docker e rode:
---
docker-compose up -d
Verifique os containers ativos:

docker ps
Visualize os logs:

docker-compose logs -f

### 4. Configurações adicionais

- Variáveis de ambiente: Configure credenciais e parâmetros sensíveis usando secrets do provedor ou variáveis de ambiente no serviço gerenciado

- Banco de dados: Para produção, considere banco Oracle gerenciado pelo provedor ou cluster com backup automático

- Domínio e HTTPS: Configure DNS e certificados SSL (via Let's Encrypt ou serviço da nuvem) para acesso seguro à API

- Escalabilidade: Utilize orquestradores como Kubernetes (EKS, AKS, GKE) para maior controle e escalonamento automático

### 5. Acesso à API na nuvem

Após subir os containers, acesse:


http://<IP-ou-DOMINIO-da-VM-ou-servico>:8080/api
Swagger disponível em:
http://<IP-ou-DOMINIO-da-VM-ou-servico>:8080/swagger-ui/index.html

---


## 🔐 Autenticação 

A API utiliza autenticação via JWT. Para obter um token válido, envie um POST para:


POST /api/auth/login

{
  "username": "Marcelo@email.com",
  "password": "Marcio12"
}

Authorization: Bearer <token>

## 📄 Exemplo de uso da API – Tipo de Usuário

### 🔍 Buscar por ID

GET /api/tipos-usuario/{id}

### 🔁 Atualizar

PUT /api/tipos-usuario/{id}

{
  "codigo": "ADMINISTRADOR",
  "descricao": "Administrador do programa"
}

### 🗑️ Deletar

DELETE /api/tipos-usuario/{id}

### 📋 Listar todos

GET /api/tipos-usuario

### ➕ Criar

POST /api/tipos-usuario
{
  "codigo": "USUARIO",
  "descricao": "Usuáriocom permissões limitadas"
}

### 🔢 Contagem total

GET /api/tipos-usuario/count

### 🔍 Buscar por código

GET /api/tipos-usuario/codigo/ADMIN


## 👤 Exemplo de uso da API – Usuário

### 🔍 Buscar por ID

GET /api/usuarios/{id}

### 🔁 Atualizar

PUT /api/usuarios/{id}

{
  "nome": "Marcelo Rogério",
  "email": "marceloroger@gmail.com",
  "senha": "markroger",
  "cpf": "12345678910",
  "tipoUsuario": {
    "id": 2
  }
}

### 🗑️ Deletar

DELETE /api/usuarios/{id}

### 📋 Listar todos

GET /api/usuarios

### ➕ Criar

POST /api/usuarios

{
  "nome": "Mariana Siqueira",
  "email": "marianasiq@gmail.com",
  "senha": "MariaSiq",
  "cpf": "98765432220",
  "tipoUsuario": {
    "id": 1
  }
}

### 🔍 Buscar por e-mail

GET /api/usuarios/email/marianasiq@gmail.com

### 🔢 Contagem total

GET /api/usuarios/count

---
## 📸 Evidências da Execução

### ✅ Execução dos containers em segundo plano (`docker-compose up -d`)
![docker-compose up](prints/Evidencia1.PNG)

### ✅ Comando `docker ps` exibindo os containers em execução
![docker ps](prints/evidencia2.PNG)

### ✅ Logs dos containers (`docker logs <nome_container>`)
![docker logs sara-api](prints/evidencia3.PNG)
![docker logs oracle-db](prints/evidencia4.PNG)


---

## 🧩 Funcionalidades principais

- Gestão de usuários e tipos de usuários  
- Cadastro e consulta de sensores e leituras  
- Emissão e gerenciamento de alertas  
- Envio e status de notificações  
- Filtragem, paginação e ordenação nas consultas  
- Segurança com JWT para proteção dos endpoints  
