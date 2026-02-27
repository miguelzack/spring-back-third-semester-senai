
# 🐾 API de Adoção de Pets

Este projeto é uma API simples para gerenciar um esquema de adoção de pets.  
Atualmente, a API possui apenas o método de **salvar pet**, mas será expandida futuramente para incluir funcionalidades como listar, atualizar e remover pets.

---

## 🚀 Tecnologias utilizadas
- Spring boot (JAVA)
- Validation
- Lombock
- MySQL Driver
- Spring Data JPA
- Spting Web
- DevTools

---

## 📌 Funcionalidades atuais
- **Salvar Pet**: permite cadastrar um novo pet para adoção.

### Exemplo de requisição
**POST** `http://localhost:8080/pet/savePet`

```json
{
  "nome": "Rex",
  "idade": 4,
  "raca": "labrador",
  "porte": "MEDIO",
  "tipo": "CACHORRO"
}

```

### Exemplo de resposta
```json
{
    SALVO
}
```

---

## 📂 Estrutura do projeto
```
/src/main/java
  ├── controllers
  ├── services
  ├── repositories
  ├── enums
  ├── dto
  └── entities
```

---

## 🔮 Próximos passos
- [ ] Listar todos os pets disponíveis para adoção
- [ ] Buscar pet por ID
- [ ] Atualizar informações de um pet
- [ ] Remover pet do sistema
- [ ] Adicionar autenticação e autorização

---

