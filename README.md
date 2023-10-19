# Microsserviços com Java Spring Boot e Kafka

Este projeto de exemplo demonstra a implementação de microsserviços utilizando Java Spring Boot e Kafka. Ele consiste em dois subprojetos: um Producer e um Consumer. O Producer funciona como uma API Rest que recebe requisições e as envia para um tópico no Kafka. O Consumer, por sua vez, coleta os eventos da fila Kafka e os processa conforme necessário.
 Neste projeto também é abordado o uso de containers Docker e do Zookeeper. 

## Estrutura do Projeto

O projeto está dividido em duas partes principais:

1. **Producer:**
   - O Producer é uma aplicação Spring Boot que expõe endpoints REST para receber requisições.
   - Ele envia as requisições para um tópico no Kafka, permitindo que outros serviços consumam esses eventos.

2. **Consumer:**
   - O Consumer é responsável por coletar eventos do tópico Kafka e processá-los de acordo com as necessidades do sistema.
   - Ele demonstra como os microsserviços podem ser usados para processar eventos de forma assíncrona e escalável.

## Utilização do Docker

Este projeto utiliza Docker para facilitar a execução e gerenciamento dos contêineres das aplicações e serviços relacionados. Um arquivo `docker-compose.yml` está disponível na raiz do projeto para simplificar a configuração dos contêineres Docker.

### Configuração do Docker

Antes de usar o Docker Compose, certifique-se de ter o Docker e o Docker Compose instalados em seu sistema.

### Executando com Docker Compose

Para iniciar o projeto com Docker Compose, siga os seguintes passos:

1. Navegue até a pasta raiz do projeto onde o arquivo `docker-compose.yml` está localizado.
```yaml
# Copyright VMware, Inc.
# SPDX-License-Identifier: APACHE-2.0

version: "3"

services:
  zookeeper:
    image: docker.io/bitnami/zookeeper:latest
    ports:
      - "2181:2181"
    volumes:
      - "zookeeper_data:/bitnami"
    environment:
      - ALLOW_ANONYMOUS_LOGIN=yes
  kafka:
    image: docker.io/bitnami/kafka:3.5
    ports:
      - "9092:9092"
    volumes:
      - "kafka_data:/bitnami"
    environment:
      # KRaft settings
      - KAFKA_CFG_NODE_ID=0
      - KAFKA_CFG_BROKER_ID=0
      # Listeners
      - KAFKA_CFG_LISTENERS=PLAINTEXT://:9092
      - KAFKA_CFG_ADVERTISED_LISTENERS=PLAINTEXT://127.0.0.1:9092
      - ALLOW_PLAINTEXT_LISTENER=yes
      - KAFKA_CFG_ZOOKEEPER_CONNECT=zookeeper:2181
    depends_on:
      - zookeeper

volumes:
  zookeeper_data:
    driver: local
  kafka_data:
    driver: local
```

3. Execute o seguinte comando para iniciar os contêineres:
   ``` powershell
   docker-compose up -d
   ```
   Note que a flag "-d" libera o terminal.
   
4. Os contêineres serão iniciados e configurados de acordo com o arquivo `docker-compose.yml`.

O arquivo `docker-compose.yml` inclui a configuração de um contêiner Zookeeper. O Zookeeper desempenha um papel fundamental na coordenação e gerenciamento de brokers Kafka, permitindo a escalabilidade e a confiabilidade do sistema. Ele é uma parte essencial da infraestrutura quando se trabalha com Kafka.

Certifique-se de que o Docker e o Docker Compose estejam instalados antes de iniciar os contêineres. Esta configuração facilita a execução do projeto em um ambiente Docker, garantindo a operação suave dos serviços Kafka e do projeto como um todo.


## Autor

Este projeto foi desenvolvido por [EuDavidReis-ODev](https://github.com/EuDavdiReis-ODev).

## Links Úteis

- LinkedIn: [Perfil no LinkedIn](https://www.linkedin.com/in/eudavidreis-dev/)
- Site Pessoal: [www.mrdev.tec.br](http://www.mrdev.tec.br)

Sinta-se à vontade para entrar em contato se tiver alguma dúvida ou feedback.
