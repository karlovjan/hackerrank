package algorithms;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;

class FilterBooleanTest {
	class BooleanValue {
		private Boolean block;

		BooleanValue(Boolean block) {
			this.block = block;
		}

		Boolean getBlock() {
			return block;
		}
	}

	@Test
	void npeTest() {

		assertThrows(NullPointerException.class, () -> List
				.of(new BooleanValue(Boolean.TRUE), new BooleanValue(Boolean.FALSE), new BooleanValue(null),
						new BooleanValue(Boolean.TRUE)).stream().filter(BooleanValue::getBlock).count());

	}

	@Test
	void okTest() {

		assertEquals(2, List.of(new BooleanValue(Boolean.TRUE), new BooleanValue(Boolean.FALSE),
				new BooleanValue(Boolean.FALSE), new BooleanValue(Boolean.TRUE)).stream().filter(BooleanValue::getBlock)
				.count());

	}
}
