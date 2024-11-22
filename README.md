<img src=".github/banner.svg" alt="Ampz" width="100%">

---

Este repositório contém o projeto **Ampz**, desenvolvido como parte da disciplina de Mobile. O objetivo do projeto é promover o consumo consciente de energia elétrica utilizando um dispositivo IoT e um sistema de gamificação interativo. A solução se destaca por combinar IoT com gamificação, promovendo o uso consciente de energia de forma didática e divertida.

---

## 🎥 Pré-visualização do projeto

TODO: Colocar vídeo aqui

---

## 🚀 Vídeo do Pitch do projeto

[Link para o vídeo](#) apresentando a solução, seus benefícios e funcionamento.  

---

## 📱 Telas desenvolvidas
- [Boas vindas](./app/src/main/java/dev/ericknathan/ampz/ui/activities/WelcomeActivity.kt)
- [Login](./app/src/main/java/dev/ericknathan/ampz/ui/activities/SignInActivity.kt)
- [Recuperação de senha](./app/src/main/java/dev/ericknathan/ampz/ui/activities/RecoverPasswordActivity.kt)
- [Página inicial com navegação]([HomeActivity](./app/src/main/java/dev/ericknathan/ampz/ui/activities/HomeActivity.kt) )
- [Página de estatísticas](./app/src/main/java/dev/ericknathan/ampz/ui/activities/pages/HomePage.kt)
- [Ranking](./app/src/main/java/dev/ericknathan/ampz/ui/activities/pages/RankingPage.kt)
- [Lista de desafios](./app/src/main/java/dev/ericknathan/ampz/ui/activities/pages/ChallengesPage.kt)
- [Perfil](./app/src/main/java/dev/ericknathan/ampz/ui/activities/pages/ProfilePage.kt)

## 🔗 Integrações
- [[POST] Login](./app/src/main/java/dev/ericknathan/ampz/repositories/AuthRepository.kt#L17)
- [[POST] Recuperação de senha](./app/src/main/java/dev/ericknathan/ampz/repositories/AuthRepository.kt#L44)
- [[DELETE] Deslogar usuário](./app/src/main/java/dev/ericknathan/ampz/repositories/AuthRepository.kt#L70)
- [[PUT] Atualizar perfil](./app/src/main/java/dev/ericknathan/ampz/repositories/AuthRepository.kt#L95)
- [[GET] Obter consumo de energia](./app/src/main/java/dev/ericknathan/ampz/repositories/ConsumptionRepository.kt#L15)
- [[GET] Obter ranking](./app/src/main/java/dev/ericknathan/ampz/repositories/ConsumptionRepository.kt#L40)
- [[GET] Obter lista de desafios](./app/src/main/java/dev/ericknathan/ampz/repositories/ConsumptionRepository.kt#L65)

## 👥 Equipe
Este projeto está sendo desenvolvido pelos seguintes membros:

- RM99565 - Erick Nathan Capito Pereira (2TDSPV)
- RM99577 - Guilherme Dias Gomes (2TDSPX)
- RM550889 - Hemily Nara da Silva (2TDSPX)
- RM550841 - Lucas Araujo Oliveira Silva (2TDSPV)
- RM99409 - Michael José Bernardi Da Silva (2TDSPX)
