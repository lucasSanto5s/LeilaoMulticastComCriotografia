# 🛡️ Leilão Virtual Multicast com Criptografia Híbrida

Este projeto implementa um **Leilão Virtual** baseado em comunicação **multicast** e segurança reforçada com **criptografia híbrida**. O sistema é dividido em **Servidor** e **Clientes**, garantindo privacidade e autenticidade nos lances.

---

## 📌 Tecnologias Utilizadas
- **Java** (para lógica do servidor e clientes)
- **Sockets UDP Multicast** (para comunicação entre clientes)
- **Criptografia RSA** (para autenticação e troca de chaves)
- **Criptografia Simétrica (AES)** (para cifrar os lances)
- **Assinaturas Digitais** (para validar requisições)

---

## 🏗 Estrutura do Projeto
```
LeilaoMulticastComCriptografia/
├── LeilaoServidor/  # Implementação do Servidor
├── LeilaoCliente/   # Implementação do Cliente
└── README.md        # Documentação do projeto
```

---

## 🚀 Como Executar o Projeto

### 1️⃣ Clonar o repositório

```bash
git clone https://github.com/lucasSanto5s/LeilaoMulticastComCriotografia.git
cd LeilaoMulticastComCriotografia
```

### 2️⃣ Compilar o código

```bash
javac LeilaoServidor/Servidor.java
javac LeilaoCliente/Cliente.java
```

### 3️⃣ Executar o Servidor

```bash
java LeilaoServidor.Servidor
```

### 4️⃣ Executar um ou mais Clientes

```bash
java LeilaoCliente.Cliente
```

---

## 🔑 Segurança com Criptografia Híbrida

### 1️⃣ Comunicação Cliente ⇄ Servidor (Entrada no Leilão)
- O cliente **envia uma requisição assinada digitalmente** com sua chave privada RSA.
- O servidor **verifica a assinatura digital** e responde com:
  - **Endereço do Grupo Multicast**
  - **Chave Simétrica AES**, cifrada com a chave pública RSA do cliente.

### 2️⃣ Comunicação Multicast (Lances do Leilão)
- Todos os clientes **usam a chave AES** recebida para **cifrar e decifrar os lances** dentro do grupo multicast.
- Apenas clientes autorizados podem entender os lances, garantindo privacidade.

---

## 📌 Funcionalidades Implementadas

### ✅ Servidor
- Publica os itens em leilão.
- Cria e gerencia um **grupo multicast**.
- Controla **tempo do leilão** e informações dos lances.
- Processa **requisições de entrada** (validação da assinatura digital).
- Envia **chave AES cifrada** e endereço multicast ao cliente autenticado.

### ✅ Cliente
- Solicita **entrada no leilão** ao servidor (com autenticação digital).
- Recebe **endereço multicast** e **chave AES** do servidor.
- Participa do leilão enviando e recebendo lances criptografados via **multicast**.

---

## 📜 Exemplo de Fluxo de Comunicação

### 1️⃣ Entrada no Leilão (Unicast)
📩 **Cliente ➡️ Servidor**  
- **Cliente envia requisição assinada** com chave privada.  
- **Servidor valida assinatura** e responde com chave AES e endereço multicast.  

🔑 **Segurança:**  
- Mensagem do servidor cifrada com **RSA (chave pública do cliente)**.  

### 2️⃣ Lances (Multicast)
📡 **Servidor 📢 Grupo Multicast**  
- Lances são transmitidos **cifrados com AES**, garantindo privacidade.  

🔑 **Segurança:**  
- Somente clientes autenticados conseguem **descriptografar** os lances.  

