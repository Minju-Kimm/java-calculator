package study;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StringsTest {

	@Test
	@DisplayName("\"1, 2\"를 ,로 split했을 때 1과 2로 잘 분리되는지 확인한다.")
	void 콤마_분리_테스트() {
		//given
		String a = "1,2";

		//when
		List<String> resultList = Arrays.asList(a.split(","));

		//then
		assertThat(resultList.contains("1,2"));
	}

	@Test
	@DisplayName("\"1\"을 ,로 split했을 때 1만을 포함하는 배열이 반환되는지 확인한다.")
	void 배열_반환_테스트() {
		//given
		String a = "1";

		//when
		List<String> resultList = Arrays.asList(a.split(","));


		//then
		assertThat(resultList).containsExactly("1");
	}

	@Test
	@DisplayName("\"(1,2)\"값이 주어질 때 String의 substring() 메소드를 활용해 ()를 제거하고 \"1,2\"를 반환하는지 확인한다.")
	void 괄호_제거_테스트() {
		//given
		String a = "(1,2)";

		//when
		String result = a.substring(1, a.length() - 1);

		//then
		assertThat(result).isEqualTo("1,2");
	}

	@Test
	@DisplayName("\"abc\"값이 주어졌을 때 String의 charAt()메소드를 활용해 특정 위치의 문자를 가져오는지 확인한다.")
	void 특정위치_문자_가져오기() {
		//given
		String a = "abc";

		//when
		// Character result = a.charAt(a.length());

		//then
		assertThatThrownBy(() -> {
			assertThat(a.charAt(a.length())).isEqualTo('d');
		}).isInstanceOf(StringIndexOutOfBoundsException.class)
			.hasMessageContaining("String index out of range: 3");
	}




}