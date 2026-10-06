package org.example.kinobackend.config;

import org.example.kinobackend.model.Movie;
import org.example.kinobackend.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class InitDataMovie implements CommandLineRunner {
    @Autowired
    MovieRepository movieRepository;

    @Override
    public void run(String... args) throws Exception {
        // 1. Bakemono No Ko (The Boy and the Beast)
        Movie movie1 = new Movie();
        movie1.setName("Bakemono No Ko");
        movie1.setRuntimeMinutes(119);
        movie1.setDescription("A human boy stumbles into a beast world and becomes the apprentice of a warrior beast.");
        movie1.setPosterUrl("https://th.bing.com/th/id/OIP.15NlhnviJtrQ_X0zfWMjuQHaK6?w=132&h=180&c=7&r=0&o=7&dpr=1.5&pid=1.7&rm=3");
        movie1.setAgeLimit(11);
        movie1.setActive(true);

        // 2. The Lord of the Rings: The Fellowship of the Ring
        Movie movie2 = new Movie();
        movie2.setName("The Fellowship of the Ring");
        movie2.setRuntimeMinutes(178);
        movie2.setDescription("A meek Hobbit from the Shire and eight companions set out on a journey to destroy the One Ring.");
        movie2.setPosterUrl("https://image.tmdb.org/t/p/w500/6oom5QYQ2yQTMJIbnvbkBL9cHo6.jpg");
        movie2.setAgeLimit(11);
        movie2.setActive(true);

        // 3. The Green Mile
        Movie movie3 = new Movie();
        movie3.setName("The Green Mile");
        movie3.setRuntimeMinutes(189);
        movie3.setDescription("A death row head guard discovers that one of his inmates has a miraculous, supernatural gift.");
        movie3.setPosterUrl("https://image.tmdb.org/t/p/w500/velWPhVMQeQKcxggNEU8YmIo52R.jpg");
        movie3.setAgeLimit(15);
        movie3.setActive(true);

        // 4. The Longest Yard
        Movie movie4 = new Movie();
        movie4.setName("The Longest Yard");
        movie4.setRuntimeMinutes(113);
        movie4.setDescription("A jailed former NFL quarterback recruits a team of inmates to play a football game against the guards.");
        movie4.setPosterUrl("https://th.bing.com/th/id/OIP.EoMsj6trlD6ufqN1DI9dFQHaKb?w=202&h=285&c=7&r=0&o=7&dpr=1.5&pid=1.7&rm=3");
        movie4.setAgeLimit(11);
        movie4.setActive(false);

        // 5. Jackass 3
        Movie movie5 = new Movie();
        movie5.setName("Jackass 3");
        movie5.setRuntimeMinutes(94);
        movie5.setDescription("Johnny Knoxville and his crew return for another round of outrageous stunts and painful pranks.");
        movie5.setPosterUrl("https://th.bing.com/th/id/OIP.EP-SQrqvlafn3mBUqF4e_wHaLl?w=199&h=312&c=7&r=0&o=7&dpr=1.5&pid=1.7&rm=3");
        movie5.setAgeLimit(15);
        movie5.setActive(false);

        movieRepository.save(movie1);
        movieRepository.save(movie2);
        movieRepository.save(movie3);
        movieRepository.save(movie4);
        movieRepository.save(movie5);

    }
}
