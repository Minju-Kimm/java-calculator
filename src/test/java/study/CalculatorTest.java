package study;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CalculatorTest {

	@Test
	@DisplayName("구분자를 기준으로 분리한 각 숫자의 합을 반환한다.")
	void 구분자_분리_합_반환() {
		//given
		String a = "1,2:3";
		int sum = 0;

		//when
		List<String> resultList = Arrays.asList(a.split("[,:]"));
		for (String result : resultList) {
			int num = Integer.parseInt(result);
			sum += num;
		}

		//then
		assertThat(sum).isEqualTo(6);
	}

	@Test
	@DisplayName("커스텀 구분자를 지정하여 분리한 각 숫자의 합을 반환한다")
	void 커스텀_구분자_분리_합_반환하기() {
		//given
		String a = "//;\n1;2;3";
		int sum = 0;

		//when
		String 기본구분자 = ",|:";

		if (a.startsWith("//")) {
			int endIndex = a.indexOf("\n");
			기본구분자 = a.substring(2, endIndex);
			a = a.substring(endIndex + 1);
		}

		List<String> result = Arrays.asList(a.split(기본구분자));
		for (String str : result) {
			int num = Integer.parseInt(str);
			sum += num;
		}
		//then
		assertThat(sum).isEqualTo(6);
	}


	@Test
	@DisplayName("숫자 이외의 값 또는 음수를 전달하는 경우 RuntimeException을 throw한다.")
	void 숫자_이외_값_또는_음수_전달_예외_발생() {
		//given
		String input1 = "//;\n1;2;-3";
		String input2 = "//;\n1;2;a";

		//when & then
		assertThatThrownBy(() -> {
			calculateSum(input1);
		}).isInstanceOf(RuntimeException.class)
			.hasMessageContaining("Invalid input: negative number or non-numeric value found");

		assertThatThrownBy(() -> {
			calculateSum(input2);
		}).isInstanceOf(RuntimeException.class)
			.hasMessageContaining("Invalid input: negative number or non-numeric value found");
	}

	private int calculateSum(String input) {
		String delimiter = ",|:"; // 기본 구분자

		// 커스텀 구분자 추출
		if (input.startsWith("//")) {
			int delimiterEndIndex = input.indexOf("\n");
			delimiter = input.substring(2, delimiterEndIndex);
			input = input.substring(delimiterEndIndex + 1); // 구분자 이후의 숫자 문자열
		}

		List<String> resultList = Arrays.asList(input.split(delimiter));
		int sum = 0;
		for (String result : resultList) {
			int num;
			try {
				num = Integer.parseInt(result);
			} catch (NumberFormatException e) {
				throw new RuntimeException("Invalid input: negative number or non-numeric value found");
			}
			if (num < 0) {
				throw new RuntimeException("Invalid input: negative number or non-numeric value found");
			}
			sum += num;
		}
		return sum;
	}

}
