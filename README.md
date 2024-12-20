<img src=".github/banner.svg" alt="Ampz" width="100%">

---

Este repositório contém o projeto **Ampz**, desenvolvido como parte da disciplina de Mobile. O objetivo do projeto é promover o consumo consciente de energia elétrica utilizando um dispositivo IoT e um sistema de gamificação interativo. A solução se destaca por combinar IoT com gamificação, promovendo o uso consciente de energia de forma didática e divertida.

---

## 🎥 Pré-visualização do projeto

https://github.com/user-attachments/assets/b294b086-1336-48d6-bd0e-2a2e2f9d6092

---

## 🚀 Vídeo do Pitch do projeto

[Link para o vídeo](https://www.youtube.com/watch?v=P3Hi4C2hoY4) apresentando a solução, seus benefícios e funcionamento.  

---

## 📱 Telas desenvolvidas
- [Boas vindas](./app/src/main/java/dev/ericknathan/ampz/ui/activities/WelcomeActivity.kt)
- [Login](./app/src/main/java/dev/ericknathan/ampz/ui/activities/SignInActivity.kt)
- [Recuperação de senha](./app/src/main/java/dev/ericknathan/ampz/ui/activities/RecoverPasswordActivity.kt)
- [Página inicial com navegação](./app/src/main/java/dev/ericknathan/ampz/ui/activities/HomeActivity.kt)
- [Página CRUD de dispositivos](./app/src/main/java/dev/ericknathan/ampz/ui/activities/pages/DevicesPage.kt)
- [Página de estatísticas](./app/src/main/java/dev/ericknathan/ampz/ui/activities/pages/HomePage.kt)
- [Ranking](./app/src/main/java/dev/ericknathan/ampz/ui/activities/pages/RankingPage.kt)
- [Lista de desafios](./app/src/main/java/dev/ericknathan/ampz/ui/activities/pages/ChallengesPage.kt)
- [Perfil](./app/src/main/java/dev/ericknathan/ampz/ui/activities/pages/ProfilePage.kt)

## 🔗 Integrações
- [[POST] Login](./app/src/main/java/dev/ericknathan/ampz/repositories/AuthRepository.kt#L17)
- [[POST] Recuperação de senha](./app/src/main/java/dev/ericknathan/ampz/repositories/AuthRepository.kt#L44)
- [[GET] Listar dispositivos](./app/src/main/java/dev/ericknathan/ampz/repositories/DeviceRepository.kt#L90)
- [[POST] Criar dispositivo](./app/src/main/java/dev/ericknathan/ampz/repositories/DeviceRepository.kt#L16)
- [[PUT] Editar dispositivo](./app/src/main/java/dev/ericknathan/ampz/repositories/DeviceRepository.kt#L41)
- [[DELETE] Excluir dispositivo](./app/src/main/java/dev/ericknathan/ampz/repositories/DeviceRepository.kt#L66)
- [[PUT] Atualizar perfil](./app/src/main/java/dev/ericknathan/ampz/repositories/AuthRepository.kt#L70)
- [[GET] Obter consumo de energia](./app/src/main/java/dev/ericknathan/ampz/repositories/ConsumptionRepository.kt#L15)
- [[GET] Obter ranking](./app/src/main/java/dev/ericknathan/ampz/repositories/ConsumptionRepository.kt#L40)
- [[GET] Obter lista de desafios](./app/src/main/java/dev/ericknathan/ampz/repositories/ConsumptionRepository.kt#L65)

---

## 👥 Equipe
Este projeto está sendo desenvolvido pelos seguintes membros:

- RM99565 - Erick Nathan Capito Pereira (2TDSPV)
- RM99577 - Guilherme Dias Gomes (2TDSPX)
- RM550889 - Hemily Nara da Silva (2TDSPX)
- RM550841 - Lucas Araujo Oliveira Silva (2TDSPV)
- RM99409 - Michael José Bernardi Da Silva (2TDSPX)
