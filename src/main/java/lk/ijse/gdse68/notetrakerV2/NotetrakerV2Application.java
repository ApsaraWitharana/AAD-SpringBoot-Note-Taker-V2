package lk.ijse.gdse68.notetrakerV2;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class NotetrakerV2Application {

	public static void main(String[] args) {
		SpringApplication.run(NotetrakerV2Application.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

}

//full mode eken register wenwa application conntext ek