package co.edu.udes.backend;

import co.edu.udes.backend.models.*;
import co.edu.udes.backend.models.inheritance.ProfileU;
import co.edu.udes.backend.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	CommandLineRunner init(ProfileURepository profileURepository) {
		return args -> {
			PermissionEntity createPermission = PermissionEntity.builder()
					.name("CREATE")
					.build();

			PermissionEntity readPermission = PermissionEntity.builder()
					.name("READ")
					.build();

			PermissionEntity updatePermission = PermissionEntity.builder()
					.name("UPDATE")
					.build();

			PermissionEntity deletePermission = PermissionEntity.builder()
					.name("DELETE")
					.build();


			RoleEntity roleAdmin = RoleEntity.builder()
					.roleEnum(RoleEnum.ADMIN)
					.permissionList(Set.of(createPermission, readPermission, updatePermission, deletePermission))
					.build();

			RoleEntity roleTeacher = RoleEntity.builder()
					.roleEnum(RoleEnum.TEACHER)
					.permissionList(Set.of(createPermission, readPermission, updatePermission))
					.build();

			RoleEntity roleStudent = RoleEntity.builder()
					.roleEnum(RoleEnum.STUDENT)
					.permissionList(Set.of(createPermission, readPermission, updatePermission))
					.build();

			RoleEntity roleEmployee = RoleEntity.builder()
					.roleEnum(RoleEnum.EMPLOYEE)
					.permissionList(Set.of(createPermission, readPermission, updatePermission))
					.build();

			ProfileU perfil1 = ProfileU.builder()
					.firstName("Soy")
					.lastName("Admin")
					.phone("123456789")
					.email("admin@udes")
					.userName("admin")
					.password(new BCryptPasswordEncoder().encode("admin"))
					.enable(true)
					.accountNonExpired(true)
					.accountNonLocked(true)
					.credentialsNonExpired(true)
					.role(roleAdmin)
					.build();

			ProfileU perfil2 = ProfileU.builder()
					.firstName("Soy")
					.lastName("Profesor")
					.phone("123456789")
					.email("profesor@udes")
					.userName("profesor")
					.password(new BCryptPasswordEncoder().encode("profesor"))
					.enable(true)
					.accountNonExpired(true)
					.accountNonLocked(true)
					.credentialsNonExpired(true)
					.role(roleTeacher)
					.build();

			ProfileU perfil3 = ProfileU.builder()
					.firstName("Soy")
					.lastName("Estudiante")
					.phone("123456789")
					.email("estudiante@udes")
					.userName("estudiante")
					.password(new BCryptPasswordEncoder().encode("estudiante"))
					.enable(true)
					.accountNonExpired(true)
					.accountNonLocked(true)
					.credentialsNonExpired(true)
					.role(roleStudent)
					.build();

			ProfileU perfil4 = ProfileU.builder()
					.firstName("Soy")
					.lastName("Empleado")
					.phone("123456789")
					.email("empleado@udes")
					.userName("empleado")
					.password(new BCryptPasswordEncoder().encode("empleado"))
					.enable(true)
					.accountNonExpired(true)
					.accountNonLocked(true)
					.credentialsNonExpired(true)
					.role(roleEmployee)
					.build();

			profileURepository.saveAll(List.of(perfil1, perfil2, perfil3, perfil4));
		};
	}

}

