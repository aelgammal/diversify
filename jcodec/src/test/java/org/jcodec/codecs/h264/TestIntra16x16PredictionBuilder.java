package org.jcodec.codecs.h264;

import org.jcodec.codecs.h264.decode.Intra16x16PredictionBuilder;
import org.jcodec.codecs.h264.decode.model.BlockBorder;
import org.jcodec.codecs.h264.decode.model.PixelBuffer;

public class TestIntra16x16PredictionBuilder extends JAVCTestCase {
	
	public void testVertical() throws Exception {
		// MB 88
		int[] expected = new int[] {
			28, 132, 205, 207, 207, 207, 207, 207, 207, 207, 207, 206, 206, 206, 206, 206,
			28, 132, 205, 207, 207, 207, 207, 207, 207, 207, 207, 206, 206, 206, 206, 206,
			28, 132, 205, 207, 207, 207, 207, 207, 207, 207, 207, 206, 206, 206, 206, 206,
			28, 132, 205, 207, 207, 207, 207, 207, 207, 207, 207, 206, 206, 206, 206, 206,
			28, 132, 205, 207, 207, 207, 207, 207, 207, 207, 207, 206, 206, 206, 206, 206,
			28, 132, 205, 207, 207, 207, 207, 207, 207, 207, 207, 206, 206, 206, 206, 206,
			28, 132, 205, 207, 207, 207, 207, 207, 207, 207, 207, 206, 206, 206, 206, 206,
			28, 132, 205, 207, 207, 207, 207, 207, 207, 207, 207, 206, 206, 206, 206, 206,
			28, 132, 205, 207, 207, 207, 207, 207, 207, 207, 207, 206, 206, 206, 206, 206,
			28, 132, 205, 207, 207, 207, 207, 207, 207, 207, 207, 206, 206, 206, 206, 206,
			28, 132, 205, 207, 207, 207, 207, 207, 207, 207, 207, 206, 206, 206, 206, 206,
			28, 132, 205, 207, 207, 207, 207, 207, 207, 207, 207, 206, 206, 206, 206, 206,
			28, 132, 205, 207, 207, 207, 207, 207, 207, 207, 207, 206, 206, 206, 206, 206,
			28, 132, 205, 207, 207, 207, 207, 207, 207, 207, 207, 206, 206, 206, 206, 206,
			28, 132, 205, 207, 207, 207, 207, 207, 207, 207, 207, 206, 206, 206, 206, 206,
			28, 132, 205, 207, 207, 207, 207, 207, 207, 207, 207, 206, 206, 206, 206, 206
		};
		
		int[] left = new int[] {-1, -1, -1, -1};
		int[] top = new int[] {28, 132, 205, 207, 207, 207, 207, 207, 207, 207, 207, 206, 206, 206, 206, 206};
		int topLeft = -1;
		BlockBorder border = new BlockBorder(left, top, topLeft);

		Intra16x16PredictionBuilder builder = new Intra16x16PredictionBuilder(8);
		int[] actual = new int[256];
		builder.predictVertical(border, new PixelBuffer(actual, 0, 4));
		
		assertArrayEquals(expected, actual);
	}
	
	public void testHorizontal() throws Exception {
		// MB 29
		int[] expected = new int[] {
			234, 234,234,234,234,234,234,234,234,234,234,234,234,234,234,234,
			233, 233,233,233,233,233,233,233,233,233,233,233,233,233,233,233, 
			232,232,232,232,232,232,232,232,232,232,232,232,232,232,232,232,
			232,232,232,232,232,232,232,232,232,232,232,232,232,232,232,232,
			234,234,234,234,234,234,234,234,234,234,234,234,234,234,234,234,
			234,234,234,234,234,234,234,234,234,234,234,234,234,234,234,234,
			233,233,233,233,233,233,233,233,233,233,233,233,233,233,233,233,
			233,233,233,233,233,233,233,233,233,233,233,233,233,233,233,233,
			235,235,235,235,235,235,235,235,235,235,235,235,235,235,235,235,
			237,237,237,237,237,237,237,237,237,237,237,237,237,237,237,237,
			237,237,237,237,237,237,237,237,237,237,237,237,237,237,237,237,
			204,204,204,204,204,204,204,204,204,204,204,204,204,204,204,204,
			165,165,165,165,165,165,165,165,165,165,165,165,165,165,165,165,
			135,135,135,135,135,135,135,135,135,135,135,135,135,135,135,135,
			125,125,125,125,125,125,125,125,125,125,125,125,125,125,125,125,
			114,114,114,114,114,114,114,114,114,114,114,114,114,114,114,114
		};
		
		int[] left = new int[] {234, 233, 232, 232, 234, 234, 233, 233, 235, 237, 237, 204, 165, 135, 125, 114};
		int[] top = new int[] {231, 231, 231, 231, 229, 227, 223, 221, 209, 207, 203, 201, 202, 202, 202, 202};
		int topLeft = 233;
		BlockBorder border = new BlockBorder(left, top, topLeft);

		Intra16x16PredictionBuilder builder = new Intra16x16PredictionBuilder(8);
		int[] actual = new int[256];
		builder.predictHorizontal(border, new PixelBuffer(actual, 0, 4));
		
		assertArrayEquals(expected, actual);
	}
	
	public void testDC() throws Exception {
		// MB 21
		int[] expected = new int[] {
			194,194,194,194,194,194,194,194,194,194,194,194,194,194,194,194,
			194,194,194,194,194,194,194,194,194,194,194,194,194,194,194,194,
			194,194,194,194,194,194,194,194,194,194,194,194,194,194,194,194,
			194,194,194,194,194,194,194,194,194,194,194,194,194,194,194,194,
			194,194,194,194,194,194,194,194,194,194,194,194,194,194,194,194,
			194,194,194,194,194,194,194,194,194,194,194,194,194,194,194,194,
			194,194,194,194,194,194,194,194,194,194,194,194,194,194,194,194,
			194,194,194,194,194,194,194,194,194,194,194,194,194,194,194,194,
			194,194,194,194,194,194,194,194,194,194,194,194,194,194,194,194,
			194,194,194,194,194,194,194,194,194,194,194,194,194,194,194,194,
			194,194,194,194,194,194,194,194,194,194,194,194,194,194,194,194,
			194,194,194,194,194,194,194,194,194,194,194,194,194,194,194,194,
			194,194,194,194,194,194,194,194,194,194,194,194,194,194,194,194,
			194,194,194,194,194,194,194,194,194,194,194,194,194,194,194,194,
			194,194,194,194,194,194,194,194,194,194,194,194,194,194,194,194,
			194,194,194,194,194,194,194,194,194,194,194,194,194,194,194,194
		};
		
		int[] left = new int[] {147, 196, 206, 213, 206, 208, 212, 214, 214, 214, 214, 214, 208, 209, 192, 154};
		int[] top = new int[] {132, 182, 204, 202, 210, 210, 210, 210, 210, 210, 183, 130, 136, 189, 194, 178};
		int topLeft = 101;
		BlockBorder border = new BlockBorder(left, top, topLeft);

		Intra16x16PredictionBuilder builder = new Intra16x16PredictionBuilder(8);
		int[] actual = new int[256];
		builder.predictDC(border, new PixelBuffer(actual, 0, 4));
		
		assertArrayEquals(expected, actual);
	}
	
	public void testPlane() throws Exception {
		
		// MB 75
		int[] expected = new int[] {
			105,111,117,124,130,136,143,149,155,162,168,174,181,187,193,200,
			104,110,116,123,129,135,142,148,154,161,167,173,180,186,192,199,
			103,109,115,122,128,134,141,147,153,160,166,172,179,185,191,198,
			102,108,114,121,127,133,140,146,152,159,165,171,178,184,190,197,
			101,107,113,120,126,132,139,145,151,158,164,170,177,183,189,196,
			100,106,112,119,125,131,138,144,150,157,163,169,176,182,188,195,
			99,105,111,118,124,130,137,143,149,156,162,168,175,181,187,194,
			98,104,110,117,123,129,136,142,148,155,161,167,174,180,186,193,
			97,103,109,116,122,128,135,141,147,154,160,166,173,179,185,192,
			96,102,108,115,121,127,134,140,146,153,159,165,172,178,184,191,
			95,101,107,114,120,126,133,139,145,152,158,164,171,177,183,190,
			94,100,106,113,119,125,132,138,144,151,157,163,170,176,182,189,
			93,99,105,112,118,124,131,137,143,150,156,162,169,175,181,188,
			92,98,104,111,117,123,130,136,142,149,155,161,168,174,180,187,
			91,97,103,110,116,122,129,135,141,148,154,160,167,173,179,186,
			90,96,102,109,115,121,128,134,140,147,153,159,166,172,178,185
		};
		
		int[] left = new int[] {97, 94, 88, 86, 92, 92, 92, 92, 91, 99, 100, 94, 96, 89, 79, 76};
		int[] top = new int[] {110, 95, 108, 158, 199, 199, 199, 199, 204, 202, 196, 193, 199, 202, 206, 208};
		int topLeft = 122;
		BlockBorder border = new BlockBorder(left, top, topLeft);

		Intra16x16PredictionBuilder builder = new Intra16x16PredictionBuilder(8);
		int[] actual = new int[256];
		builder.predictPlane(border, new PixelBuffer(actual, 0, 4));
		
		assertArrayEquals(expected, actual);
	}
}