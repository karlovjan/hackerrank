package algorithms;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

public class FilterBooleanTest {
	class BooleanValue {
		private Boolean block;

		public BooleanValue(Boolean block) {
			this.block = block;
		}

		public Boolean getBlock() {
			return block;
		}
	}

	@Test
	void tallestCandlesCountTest() {

		assertThrows(NullPointerException.class, () -> List
				.of(new BooleanValue(Boolean.TRUE), new BooleanValue(Boolean.FALSE), new BooleanValue(null),
						new BooleanValue(Boolean.TRUE)).stream().filter(BooleanValue::getBlock)
				.collect(Collectors.counting()));

	}
}
