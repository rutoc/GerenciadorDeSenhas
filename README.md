# Sistema de Gerenciamento de Senhas com Filas Prioritárias

![Java](https://img.shields.io/badge/Java-17%2B-blue)
![JUnit5](https://img.shields.io/badge/JUnit-5.8.2-green)
![License](https://img.shields.io/badge/License-MIT-yellow)

Gerenciamento de senhas em estabelecimentos comerciais, implementando filas prioritárias com estruturas de dados customizadas em Java.

## 📌 Objetivo

Este projeto foi desenvolvido para demonstrar na prática:
- Implementação de estruturas de dados personalizadas (Lista Ligada e Fila)
- Gerenciamento de filas com prioridades (senhas preferenciais)
- Princípios de Clean Code e boas práticas de desenvolvimento
- Testes unitários abrangentes

## ✨ Funcionalidades

- ✅ Geração de senhas normais e preferenciais
- ✅ Atendimento prioritário (FIFO com prioridade para preferenciais)
- ✅ Controle de tentativas de atendimento (3 tentativas antes de descartar)
- ✅ Relatórios de desempenho:
  - Tempo médio de espera
  - Percentual de atendimentos por tipo
  - Senhas descartadas

## 🏗️ Estrutura do Código

```
src/
├── main/
│   ├── java/
│   │   ├── sistema/
│   │   │   ├── Senha.java         # Modelo de dados da senha
│   │   │   ├── No.java           # Nó da lista ligada
│   │   │   ├── ListaLigada.java   # Implementação de lista duplamente ligada
│   │   │   ├── Fila.java         # Fila baseada na lista ligada
│   │   │   ├── GerenciadorSenhas.java # Lógica principal
│   │   │   └── Main.java         # Ponto de entrada
├── test/
│   ├── java/
│   │   ├── sistema/
│   │   │   ├── SenhaTest.java     # Testes para classe Senha
│   │   │   └── FilaTest.java      # Testes para classe Fila
```

## � Como Executar

### Pré-requisitos
- Java JDK 17+
- Maven (opcional)

### Execução
1. Clone o repositório.

2. Compile e execute.

### Testes
Para executar os testes unitários:
```bash
mvn test
```

## 🧪 Testes Implementados

Cobertura de testes para:
- Transições de estado da senha (atendida/descartada)
- Comportamento FIFO da fila
- Validações de entrada

Exemplo de teste:
```java
@Test
void senhaDeveSerDescartadaApos3Tentativas() {
    Senha senha = new Senha(1, "normal");
    senha.incrementarTentativa();
    senha.incrementarTentativa();
    senha.incrementarTentativa();
    assertTrue(senha.isDescartada());
}
```

## � Clean Code

Princípios aplicados:
- Nomes significativos (ex: `marcarComoAtendida()`)
- Métodos curtos com única responsabilidade
- Validações robustas
- Imutabilidade onde possível
- Tratamento adequado de erros

## 📝 Licença

Este projeto está licenciado sob a licença MIT - veja o arquivo [LICENSE](LICENSE) para detalhes.

## 👥 Autores

- **Raphael** - [@rutoc](https://github.com/rutoc)
- **Pablo** - [@PabloMarcondesGS](https://github.com/PabloMarcondesGS)
