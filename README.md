<h1 align="center">BoxOffice</h1></br>

<p align="center">  
A box office movie app using Jetpack Compose and Hilt based on modern Android tech stacks. <br> 
It also provides weekly box office rankings using IMDB's box office API.
</p>

## Screenshots

<p align="center">
  <img src="preview/image1.gif" width="270"/>
  <img src="preview/image2.gif" width="270"/>
  <img src="preview/image3.gif" width="270"/>
</p>

<br/>

## How to build on your environment

Add your [IMDB](https://imdb-api.com/) API key in local.properties file.

```properties
api.key=YOUR_IMDB_API_KEY
```

## Tech Stack & Open Source libraries

- Minimum SDK level 23
- 100% Kotlin based + Coroutines + Flor for asynchronous.
- Hilt for dependency injection.
- Jetpack
  - Compose - A modern toolkit for building native Android UI.
  - ViewModel - UI related data holder, lifecycle aware.
- Architecture
  - MVVM Architecture
- Retrofit - construct the REST APIs and paging network data.
- [Accompanist](https://github.com/google/accompanist) - A collection of extension libraries for Jetpack Compose.
- [KenBurnsView](https://github.com/flavioarfaria/KenBurnsView) - Creates an immersive experience by animating.
- [Landscapist](https://github.com/skydoves/landscapist) - Jetpack Compose image loading library with shimmer & circular reveal animations.
- [Lottie](https://airbnb.io/lottie/#/) - Animation.
