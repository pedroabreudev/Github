<h1>Github App</h1>

This app is made to query a user's github repositories and repository details. Development with Jetpack Compose, Koin, Coroutines, LiveData, Jetpack (ViewModel) and Material Design using MVVM architecture.

## Tech stack & Open-source libraries
- Language
  - [Kotlin] (https://kotlinlang.org/)
    
- Architecture
  - MVVM Architecture (Model View ViewModel)

- Libraries
  - Jetpack Compose
  - [Retrofit2](https://github.com/square/retrofit): Construct the REST APIs and paging network data.
  - ViewModel: Manages UI-related data holder and lifecycle aware. Allows data to survive configuration changes such as screen rotations.
  - [Koin](https://insert-koin.io/docs/quickstart/android-compose): for dependency injection.
  - LiveData
  - Coroutines

## Test
- JUnit
- Mockito


## Configuração do Ambiente
1. Clone este repositório
2. Abra o projeto no Android Studio.
3. Sincronize o projeto com os arquivos Gradle.
4. Execute o aplicativo em um emulador ou dispositivo Android.
5. Insira no HomeScreen o nome de usuário do Github que deseja consultar a listagem de repositórios e ver os detalhes.

## API do GitHub

Neste aplicativo foi utilizado a API pública do GitHub. Para mais informações, consulte a [documentação oficial da API do GitHub](https://docs.github.com/en/rest).

## Licença

Este projeto é licenciado sob a Licença MIT. Consulte o arquivo LICENSE para obter mais informações.

