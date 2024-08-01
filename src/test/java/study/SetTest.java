package study;

import static org.assertj.core.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class SetTest {
	private Set<Integer> numbers;

	@BeforeEach
	void setUp() {
		numbers = new HashSet<>();
		numbers.add(1);
		numbers.add(1);
		numbers.add(2);
		numbers.add(3);
	}

	/*-------------TEST CASE 구현 START-------------*/

	@DisplayName("set의 크기를 확인한다.")
	@Test
	void set_크기_확인하기() {
		//given
		int sizes = numbers.size();

		//when

		//then
		assertThat(sizes == 3).isTrue(); //중복된 값은 추가되지 않았으므로
	}

	@DisplayName("1, 2, 3의 값이 존재하는지 확인한다.")
	@ParameterizedTest
	@ValueSource(ints = {1, 2, 3})
	void 값_존재_확인하기(int num) {
		//given

		//when

		//then
		assertThat(numbers.contains(num));
	}

	@DisplayName("1, 2, 3의 값이 존재하는지 확인한다.")
	@ParameterizedTest
	@ValueSource(ints = {1, 2, 3, 4, 5})
	void 값_존재_확인하기_contains_fail_case(int num) {
		//given

		//when
		boolean result = numbers.contains(num);

		//then
		if (num == 1 || num == 2 || num == 3) { //값이 존재하는 경우
			org.junit.jupiter.api.Assertions.assertEquals(true, result);
		} else {
			org.junit.jupiter.api.Assertions.assertEquals(false, result);
		}

	}



}
