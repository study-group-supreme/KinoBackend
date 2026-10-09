package org.example.kinobackend.config;

import org.example.kinobackend.model.Category;
import org.example.kinobackend.model.Movie;
import org.example.kinobackend.repository.CategoryRepository;
import org.example.kinobackend.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@Order(2)
public class InitDataMovie implements CommandLineRunner {
    @Autowired
    MovieRepository movieRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public void run(String... args) throws Exception {

        //Categories
        Category action = new Category();
        action.setName("Action");

        Category horror = new Category();
        horror.setName("Horror");

        Category comedy = new Category();
        comedy.setName("Comedy");

        Category fantasy = new Category();
        fantasy.setName("Fantasy");

        Category adventure = new Category();
        adventure.setName("Adventure");

        Category drama = new Category();
        drama.setName("Drama");

        Category crime = new Category();
        crime.setName("Crime");

        categoryRepository.save(action);
        categoryRepository.save(horror);
        categoryRepository.save(comedy);
        categoryRepository.save(fantasy);
        categoryRepository.save(adventure);
        categoryRepository.save(drama);
        categoryRepository.save(crime);


        // 1. Bakemono No Ko (The Boy and the Beast)
        Movie movie1 = new Movie();
        movie1.setName("Bakemono No Ko");
        movie1.setRuntimeMinutes(119);
        movie1.setDescription("A human boy stumbles into a beast world and becomes the apprentice of a warrior beast.");
        movie1.setPosterUrl("https://th.bing.com/th/id/OIP.15NlhnviJtrQ_X0zfWMjuQHaK6?w=132&h=180&c=7&r=0&o=7&dpr=1.5&pid=1.7&rm=3");
        movie1.setAgeLimit(11);
        movie1.setActive(true);
        movie1.setCategories(Set.of(adventure, action, drama, fantasy));

        // 2. The Lord of the Rings: The Fellowship of the Ring
        Movie movie2 = new Movie();
        movie2.setName("The Fellowship of the Ring");
        movie2.setRuntimeMinutes(178);
        movie2.setDescription("A meek Hobbit from the Shire and eight companions set out on a journey to destroy the One Ring.");
        movie2.setPosterUrl("https://image.tmdb.org/t/p/w500/6oom5QYQ2yQTMJIbnvbkBL9cHo6.jpg");
        movie2.setAgeLimit(11);
        movie2.setActive(true);
        movie2.setCategories(Set.of(fantasy, adventure, drama));

        // 3. The Green Mile
        Movie movie3 = new Movie();
        movie3.setName("The Green Mile");
        movie3.setRuntimeMinutes(189);
        movie3.setDescription("A death row head guard discovers that one of his inmates has a miraculous, supernatural gift.");
        movie3.setPosterUrl("https://image.tmdb.org/t/p/w500/velWPhVMQeQKcxggNEU8YmIo52R.jpg");
        movie3.setAgeLimit(15);
        movie3.setActive(true);
        movie3.setCategories(Set.of(crime, drama, fantasy));

        // 4. The Longest Yard
        Movie movie4 = new Movie();
        movie4.setName("The Longest Yard");
        movie4.setRuntimeMinutes(113);
        movie4.setDescription("A jailed former NFL quarterback recruits a team of inmates to play a football game against the guards.");
        movie4.setPosterUrl("https://th.bing.com/th/id/OIP.EoMsj6trlD6ufqN1DI9dFQHaKb?w=202&h=285&c=7&r=0&o=7&dpr=1.5&pid=1.7&rm=3");
        movie4.setAgeLimit(11);
        movie4.setActive(false);
        movie4.setCategories(Set.of(comedy, crime));

        // 5. Jackass 3
        Movie movie5 = new Movie();
        movie5.setName("Jackass 3");
        movie5.setRuntimeMinutes(94);
        movie5.setDescription("Johnny Knoxville and his crew return for another round of outrageous stunts and painful pranks.");
        movie5.setPosterUrl("https://th.bing.com/th/id/OIP.EP-SQrqvlafn3mBUqF4e_wHaLl?w=199&h=312&c=7&r=0&o=7&dpr=1.5&pid=1.7&rm=3");
        movie5.setAgeLimit(15);
        movie5.setActive(false);
        movie5.setCategories(Set.of(action, comedy));

        // 6. Children of Men
        Movie movie6 = new Movie();
        movie6.setName("Children of Men");
        movie6.setRuntimeMinutes(109);
        movie6.setDescription("A dystopian classic about a world struck by global infertility, and all the societal ills that brings with it");
        movie6.setPosterUrl("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQkbc0yZeFEwC8dXr7j5dYQcXfVm3JZdPi7sep57nBnYA&s=10");
        movie6.setAgeLimit(15);
        movie6.setActive(true);
        movie6.setCategories(Set.of(drama));

        // 7. Hereditary
        Movie movie7 = new Movie();
        movie7.setName("Hereditary");
        movie7.setRuntimeMinutes(127);
        movie7.setDescription("Dysfunctional family dynamics and a horrible tragedy melt together into complete nightmare fuel in this absolutely disgusting horror classic");
        movie7.setPosterUrl("https://encrypted-tbn2.gstatic.com/shopping?q=tbn:ANd9GcTA3DbExi0VI_Gmkpo8F7X0n_bWOy77J1glajg0rmLnlSmKXYoHFCnWtVFxXcmbOJGr1_kc-gRIjSXU1i_CiNA-XIJiQ_uebbWkkgoi6pwmDHwISubM6ICL&usqp=CAc");
        movie7.setAgeLimit(15);
        movie7.setActive(true);
        movie7.setCategories(Set.of(drama, horror));

        // 8. The Skeleton Twins
        Movie movie8 = new Movie();
        movie8.setName("The Skeleton Twins");
        movie8.setRuntimeMinutes(93);
        movie8.setDescription("A twin brother and sister attempt to navigate a mish-mash of past family trauma and suicidal ideation in this heart-wrenching dark comedy");
        movie8.setPosterUrl("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTor17UMDVyd6mHYURaix7MPaPK1VPsWaORx32F4SvMlg&s");
        movie8.setAgeLimit(11);
        movie8.setActive(true);
        movie8.setCategories(Set.of(drama, comedy));

        //Obsession
        Movie movie9 = new Movie();
        movie9.setName("Obsession");
        movie9.setActive(true);
        movie9.setRuntimeMinutes(109);
        movie9.setAgeLimit(18);
        movie9.setDescription("Obsession (2025) is a horror thriller about a shy man who uses a mysterious wish-granting object to make the girl he loves fall in love with him. At first, everything seems perfect, but her love soon turns into a dangerous and terrifying obsession.");
        movie9.setCategories(Set.of(horror));
        movie9.setPosterUrl("https://image.tmdb.org/t/p/original//bRwnj8WEKBCvmfeUNOukJPwB43K.jpg");


        Movie movie10 = new Movie();
        movie10.setName("21 Jump Street");
        movie10.setRuntimeMinutes(109);
        movie10.setCategories(Set.of(comedy));
        movie10.setDescription("21 Jump Street (2012) is a comedy about two former high school rivals who become police officers and are sent undercover as students at a high school.");
        movie10.setActive(true);
        movie10.setAgeLimit(16);
        movie10.setPosterUrl("https://m.media-amazon.com/images/M/MV5BMTg2NjJiODctM2IyMS00MmQ5LWI1YmQtNTBjMTI4M2U2YzA5XkEyXkFqcGc@._V1_.jpg");

        Movie movie11 = new Movie();
        movie11.setActive(true);
        movie11.setName("Avengers: Doomsday");
        movie11.setAgeLimit(15);
        movie11.setDescription("Avengers: Doomsday — Marvel’s heroes unite to face Doctor Doom, a powerful new threat that could destroy the multiverse.");
        movie11.setCategories(Set.of(action));
        movie11.setRuntimeMinutes(150);
        movie11.setPosterUrl("https://image.tmdb.org/t/p/original/9qN1HpgEstLfkxKFR1Gnzcpr0Xc.jpg");

        Movie movie12 = new Movie();
        movie12.setName("Halloween");
        movie12.setActive(true);
        movie12.setAgeLimit(18);
        movie12.setDescription("Halloween — A masked killer, Michael Myers, escapes and returns to hunt innocent people on Halloween night.");
        movie12.setCategories(Set.of(horror));
        movie12.setRuntimeMinutes(91);
        movie12.setPosterUrl("https://m.media-amazon.com/images/I/613nfsDT4VL.jpg");

        Movie movie13 = new Movie();
        movie13.setName("The Dark Knight");
        movie13.setRuntimeMinutes(152);
        movie13.setCategories(Set.of(action, crime));
        movie13.setPosterUrl("");
        movie13.setActive(true);
        movie13.setDescription("The Dark Knight — Batman faces the Joker, a ruthless criminal who throws Gotham City into chaos.");
        movie13.setAgeLimit(15);
        movie13.setPosterUrl("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRrdFVAl4j0F_HDf2jwKDBKNo3jq9Gkif9YsSqXyhl9Qw&s=10");

        Movie movie14 = new Movie();
        movie14.setName("Minions & Monsters");
        movie14.setRuntimeMinutes(90);
        movie14.setCategories(Set.of(comedy, adventure));
        movie14.setAgeLimit(2);
        movie14.setActive(true);
        movie14.setDescription("Minions and Monsters — The Minions set out to make a monster movie but accidentally unleash real monsters.");
        movie14.setPosterUrl("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTOL-0gWLyJv2MUoEF0xQzbkcqw8SvQRDFLTWUplSTDrQ&s=10");

        Movie movie15 = new Movie();
        movie15.setName("Borat: Cultural Learnings of America");
        movie15.setDescription("Borat — A clueless journalist from Kazakhstan travels across America, causing outrageous and hilarious situations.");
        movie15.setCategories(Set.of(comedy));
        movie15.setActive(true);
        movie15.setAgeLimit(15);
        movie15.setRuntimeMinutes(84);
        movie15.setPosterUrl("https://m.media-amazon.com/images/M/MV5BMTk0MTQ3NDQ4Ml5BMl5BanBnXkFtZTcwOTQ3OTQzMw@@._V1_FMjpg_UX1000_.jpg");


        movieRepository.save(movie1);
        movieRepository.save(movie2);
        movieRepository.save(movie3);
        movieRepository.save(movie4);
        movieRepository.save(movie5);
        movieRepository.save(movie6);
        movieRepository.save(movie7);
        movieRepository.save(movie8);
        movieRepository.save(movie8);
        movieRepository.save(movie9);
        movieRepository.save(movie10);
        movieRepository.save(movie11);
        movieRepository.save(movie12);
        movieRepository.save(movie13);
        movieRepository.save(movie14);
        movieRepository.save(movie15);

    }
}
