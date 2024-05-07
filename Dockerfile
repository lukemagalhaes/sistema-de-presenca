# Estágio de construção
FROM ubuntu:latest AS build

# Atualiza os repositórios e instala o curl
RUN apt-get update && apt-get install -y curl

# Instala o OpenJDK 17 e Maven para o backend Spring Boot
RUN apt-get install -y openjdk-17-jdk maven

# Define o diretório de trabalho
WORKDIR /app

# Copia os arquivos do projeto
COPY . .

# Constrói o frontend Angular
WORKDIR /app/Angular-sistem-de-presenca

# Instala o Node.js e npm diretamente do repositório padrão do Ubuntu
RUN apt-get install -y nodejs npm

# Instala o Angular CLI
RUN npm install -g @angular/cli@17.3.6

# Compila o aplicativo Angular
RUN ng build --prod

# Volta ao diretório raiz do projeto
WORKDIR /app

# Constrói o backend Spring Boot
RUN mvn clean install

# Estágio de execução
FROM nginx:1.17.1-alpine

# Copia os arquivos compilados do Angular para o diretório de conteúdo do Nginx
COPY --from=build /app/Angular-sistem-de-presenca/dist /usr/share/nginx/html

# Define a porta que a aplicação irá expor
EXPOSE 80

# Inicia o Nginx
CMD ["nginx", "-g", "daemon off;"]

# Estágio de execução para o Spring Boot
FROM openjdk:17-jdk-slim

# Define a porta que a aplicação irá expor
EXPOSE 8080

# Define o diretório de trabalho
WORKDIR /app

# Copia o JAR do backend Spring Boot
COPY --from=build /app/target/sistema-de-presenca-1.0.0.jar app.jar

# Define o comando de inicialização da aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
