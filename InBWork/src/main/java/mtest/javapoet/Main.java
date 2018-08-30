package mtest.javapoet;

import java.io.IOException;

import javax.lang.model.element.Modifier;

import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

//JavaPoet 是一个用来生成 .java源文件的Java API。
public class Main {

	public static void main(String[] args) {
		FieldSpec fiel = FieldSpec.builder(TypeName.BOOLEAN, "chen", Modifier.PUBLIC, Modifier.STATIC)
				.initializer("$L", false).build();

		MethodSpec main = MethodSpec.methodBuilder("main").addModifiers(Modifier.PUBLIC, Modifier.STATIC)
				.returns(void.class).addParameter(String[].class, "args")
				.addStatement("$T.out.println($S)", System.class, "Hello, JavaPoet!").build();

		TypeSpec helloWorld = TypeSpec.classBuilder("HelloWorld").addModifiers(Modifier.PUBLIC, Modifier.FINAL)
				.addMethod(main).addField(fiel).build();

		JavaFile javaFile = JavaFile.builder("com.example.helloworld", helloWorld).build();

		try {
			javaFile.writeTo(System.out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
