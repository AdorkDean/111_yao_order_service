package com.rc.openapi.util;

import java.util.Comparator;
import java.util.List;

public class SortUtil {
	public static final double FSD_ASC = 1;
	public static final double FSD_DESC = -1;

	private SortUtil() {
	}

	/**
	 * * 快速排序 Double 实现
	 * 
	 * @param arr
	 *            double数组
	 * @param arrPlus
	 *            String数组
	 * @param isAsc
	 *            是否升序
	 */
	public static void sortBy(double[] arr, String[] arrPlus, boolean isAsc) {
		if (arr == null) {
			return;
		}
		double mult = isAsc ? FSD_ASC : FSD_DESC;

		int left = 0;
		int right = arr.length - 1;
		partSortBy(arr, arrPlus, left, right, mult);
	}

	public static void partSortBy(double[] arr, String[] arrPlus, int left,
			int right, double mult) {
		if (arr == null
				|| (arr != null && arr.length < 1)
				|| left >= right
				|| (arr != null && arrPlus != null && arrPlus.length < arr.length)) {
			return;
		}

		int newLeft, newRight;
		double strMidd, dblTemp;
		String strTmp = null;

		newLeft = left;
		newRight = right;
		strMidd = arr[(newLeft + newRight) / 2];

		do {
			while (mult * arr[newLeft] < mult * strMidd) {
				newLeft++;
			}

			while (mult * arr[newRight] > mult * strMidd) {
				newRight--;
			}

			if (newLeft <= newRight) {
				dblTemp = arr[newLeft];
				arr[newLeft] = arr[newRight];
				arr[newRight] = dblTemp;
				if (arrPlus != null) {
					strTmp = arrPlus[newLeft];
					arrPlus[newLeft] = arrPlus[newRight];
					arrPlus[newRight] = strTmp;
				}
				newLeft++;
				newRight--;
			}
		} while (newLeft <= newRight);

		if (newRight > left) {
			partSortBy(arr, arrPlus, left, newRight, mult);
		}
		if (newLeft < right) {
			partSortBy(arr, arrPlus, newLeft, right, mult);
		}
	}

	/**
	 * 快速排序 String 实现
	 * 
	 * @param arr
	 *            arr
	 * @param arrPlus
	 *            arrPlus
	 * @param isAsc
	 *            isAsc
	 */
	public static void sortBy(String[] arr, String[] arrPlus, boolean isAsc) {
		if (arr == null) {
			return;
		}
		double mult = isAsc ? FSD_ASC : FSD_DESC;
		int left = 0;
		int right = arr.length - 1;
		partSortBy(arr, arrPlus, left, right, mult);
	}

	public static void partSortBy(String[] arr, String[] arrPlus, int left,
			int right, double mult) {
		if (arr == null
				|| (arr != null && arr.length < 1)
				|| left >= right
				|| (arr != null && arrPlus != null && arrPlus.length < arr.length)) {
			return;
		}

		int newLeft, newRight;
		String strMidd, strTemp;

		newLeft = left;
		newRight = right;

		strMidd = arr[(newLeft + newRight) / 2];

		do {
			while (mult * arr[newLeft].compareTo(strMidd) < 0) {
				newLeft++;
			}
			while (mult * arr[newRight].compareTo(strMidd) > 0) {
				newRight--;
			}
			if (newLeft <= newRight) {
				strTemp = arr[newLeft];
				arr[newLeft] = arr[newRight];
				arr[newRight] = strTemp;
				if (arrPlus != null) {
					strTemp = arrPlus[newLeft];
					arrPlus[newLeft] = arrPlus[newRight];
					arrPlus[newRight] = strTemp;
				}
				newLeft++;
				newRight--;
			}
		} while (newLeft <= newRight);

		if (newRight > left) {
			partSortBy(arr, arrPlus, left, newRight, mult);
		}
		if (newLeft < right) {
			partSortBy(arr, arrPlus, newLeft, right, mult);
		}
	}

	/**
	 * 快速排序 boolean 实现
	 * 
	 * @param arr
	 *            arr
	 * @param arrPlus
	 *            arrPlus
	 * @param isAsc
	 *            isAsc
	 */
	public static void sortBy(boolean[] arr, boolean[] arrPlus, boolean isAsc) {
		if (arr == null) {
			return;
		}

		int left = 0;
		int right = arr.length - 1;
		partSortBy(arr, arrPlus, left, right, !isAsc);
	}

	/**
	 * 部分排序
	 * 
	 * @param arr
	 *            boolean[]
	 * @param aPlus
	 *            boolean[]
	 * @param left
	 *            int
	 * @param right
	 *            int
	 * @param isAsc
	 *            boolean
	 */
	public static void partSortBy(boolean[] arr, boolean[] aPlus, int left,
			int right, boolean isAsc) {
		if (arr == null || (arr != null && arr.length < 1) || left >= right
				|| (arr != null && aPlus != null && aPlus.length < arr.length)) {
			return;
		}

		int newLeft, newRight;
		boolean boolMidd, boolTemp;

		newLeft = left;
		newRight = right;

		boolMidd = arr[(newLeft + newRight) / 2];

		do {
			while (arr[newLeft] && !boolMidd && isAsc) {
				newLeft++;
			}
			while (!arr[newRight] && boolMidd && isAsc) {
				newRight--;
			}
			if (newLeft <= newRight) {
				boolTemp = arr[newLeft];
				arr[newLeft] = arr[newRight];
				arr[newRight] = boolTemp;
				if (aPlus != null) {
					boolTemp = aPlus[newLeft];
					aPlus[newLeft] = aPlus[newRight];
					aPlus[newRight] = boolTemp;
				}
				newLeft++;
				newRight--;
			}
		} while (newLeft <= newRight);

		if (newRight > left) {
			partSortBy(arr, aPlus, left, newRight, isAsc);
		}
		if (newLeft < right) {
			partSortBy(arr, aPlus, newLeft, right, isAsc);
		}
	}

	/**
	 * 快速排序 Object实现
	 * 
	 * @param arr
	 *            Object[]
	 * @param arrPlus
	 *            Object[]
	 * @param cp
	 *            Comparator
	 * @param isAsc
	 *            boolean
	 */
	public static void sortBy(Object[] arr, Object[] arrPlus, Comparator cp,
			boolean isAsc) {
		if (arr == null) {
			return;
		}
		double mult = isAsc ? FSD_ASC : FSD_DESC;
		int left = 0;
		int right = arr.length - 1;

		partSortBy(arr, arrPlus, cp, left, right, mult);
	}

	/**
	 * 部分排序
	 * 
	 * @param arr
	 *            Object[]
	 * @param arrPlus
	 *            Object[]
	 * @param cp
	 *            Comparator
	 * @param left
	 *            int
	 * @param right
	 *            int
	 * @param mult
	 *            double
	 */
	public static void partSortBy(Object[] arr, Object[] arrPlus,
			Comparator cp, int left, int right, double mult) {
		// Log.out.dPrintln("sort.qs2(obj[]):left is " + left + ", right is " +
		// right);

		if (arr == null
				|| (arr != null && arr.length < 1)
				|| left >= right
				|| (arr != null && arrPlus != null && arrPlus.length < arr.length)) {
			// Log.out.dPrintln("Sort.sort(obj[]). error");
			return;
		}

		int newLeft, newRight;
		Object objMidd, objTemp;

		newLeft = left;
		newRight = right;

		objMidd = arr[(newLeft + newRight) / 2];

		do {
			while (mult * cp.compare(arr[newLeft], objMidd) < 0) {
				newLeft++;
			}
			while (mult * cp.compare(arr[newRight], objMidd) > 0) {
				newRight--;

			}
			if (newLeft <= newRight) {
				objTemp = arr[newLeft];
				arr[newLeft] = arr[newRight];
				arr[newRight] = objTemp;

				if (arrPlus != null) {
					objTemp = arrPlus[newLeft];
					arrPlus[newLeft] = arrPlus[newRight];
					arrPlus[newRight] = objTemp;
				}
				newLeft++;
				newRight--;
			}
		} while (newLeft <= newRight);

		if (newRight > left) {
			partSortBy(arr, arrPlus, cp, left, newRight, mult);
		}
		if (newLeft < right) {
			partSortBy(arr, arrPlus, cp, newLeft, right, mult);
		}
	}

	/**
	 * 快速排序 List 实现
	 * 
	 * @param list
	 *            List
	 * @param listPlus
	 *            List
	 * @param cp
	 *            Comparator
	 * @param isAsc
	 *            boolean
	 */
	public static void sortBy(List list, List listPlus, Comparator cp,
			boolean isAsc) {
		if (list == null) {
			return;
		}
		double mult = isAsc ? FSD_ASC : FSD_DESC;
		int left = 0;
		int right = listPlus.size() - 1;

		partSortBy(list, listPlus, cp, left, right, mult);
	}

	/**
	 * 部分排序
	 * 
	 * @param objList
	 *            List
	 * @param objListPlus
	 *            List
	 * @param cp
	 *            Comparator
	 * @param left
	 *            int
	 * @param right
	 *            int
	 * @param mult
	 *            double
	 */
	public static void partSortBy(List objList, List objListPlus,
			Comparator cp, int left, int right, double mult) {
		// Log.out.dPrintln("sort.qs2(obj[]):left is " + left + ", right is " +
		// right);

		if (objList == null
				|| (objList != null && objList.size() < 1)
				|| left >= right
				|| (objList != null && objListPlus != null && objListPlus
						.size() < objList.size())) {
			// Log.out.dPrintln("Sort.sort(obj[]). error");
			return;
		}

		int newLeft, newRight;
		Object objMidd, objTemp;

		newLeft = left;
		newRight = right;

		objMidd = objList.get((newLeft + newRight) / 2);

		do {
			while (mult * cp.compare(objList.get(newLeft), objMidd) < 0) {
				newLeft++;
			}
			while (mult * cp.compare(objList.get(newRight), objMidd) > 0) {
				newRight--;

			}
			if (newLeft <= newRight) {
				objTemp = objList.get(newLeft);
				objList.set(newLeft, objList.get(newRight));
				objList.set(newRight, objTemp);

				if (objListPlus != null) {
					objTemp = objListPlus.get(newLeft);
					objListPlus.set(newLeft, objListPlus.get(newRight));
					objListPlus.set(newRight, objTemp);
				}
				newLeft++;
				newRight--;
			}
		} while (newLeft <= newRight);

		if (newRight > left) {
			partSortBy(objList, objListPlus, cp, left, newRight, mult);
		}
		if (newLeft < right) {
			partSortBy(objList, objListPlus, cp, newLeft, right, mult);
		}
	}

	/**
	 * 获取用于记录排序规则的String[]
	 * 
	 * @param length
	 *            length
	 * @return String[]
	 */
	public static String[] getSortStrArr(int length) {
		if (length <= 0) {
			return null;
		}

		String[] strArr = new String[length];

		for (int i = 0; i < strArr.length; i++) {
			strArr[i] = String.valueOf(i);
		}

		return strArr;
	}

	/**
	 * 将Object[]按照String[]的信息排序 String[] 的内容为经过排序后的 getSrotStrArr()的 String[]
	 * 如：{"2","3","5","4","0","1"};
	 * 
	 * @param sortArr
	 *            sortArr
	 * @param strArr
	 *            strArr
	 */
	public static void sortOnStrArr(Object[] sortArr, String[] strArr) {
		if (sortArr == null || strArr == null) {
			return;
		}
		if (sortArr.length != strArr.length || sortArr.length == 0) {
			return;
		}

		// 需要判断strArr是否这种特殊的 String[]

		int iPos;

		Object[] theArr = new Object[sortArr.length];

		for (int i = 0; i < theArr.length; i++) {
			theArr[i] = sortArr[i];
		}

		for (int i = 0; i < strArr.length; i++) {
			iPos = Integer.parseInt(strArr[i]);
			sortArr[i] = theArr[iPos];
		}
	}

	/**
	 * 快速排序 boolean 实现
	 * 
	 * @param sortArr
	 *            boolean[]
	 * @param strArr
	 *            String[]
	 */
	public static void sortOnStrArr(boolean[] sortArr, String[] strArr) {
		if (sortArr == null || strArr == null) {
			return;
		}
		if (sortArr.length != strArr.length || sortArr.length == 0) {
			return;
		}

		// 需要判断strArr是否这种特殊的 String[]

		int iPos;

		boolean[] theArr = new boolean[sortArr.length];
		for (int i = 0; i < theArr.length; i++) {
			theArr[i] = sortArr[i];
		}

		for (int i = 0; i < strArr.length; i++) {
			iPos = Integer.parseInt(strArr[i]);
			sortArr[i] = theArr[iPos];
		}
	}

	/**
	 * 快速排序 double 实现
	 * 
	 * @param sortArr
	 *            double[]
	 * @param strArr
	 *            String[]
	 */
	public static void sortOnStrArr(double[] sortArr, String[] strArr) {
		if (sortArr == null || strArr == null) {
			return;
		}
		if (sortArr.length != strArr.length || sortArr.length == 0) {
			return;
		}
		// 需要判断strArr是否这种特殊的 String[]
		int iPos;

		double[] theArr = new double[sortArr.length];
		for (int i = 0; i < theArr.length; i++) {
			theArr[i] = sortArr[i];
		}

		for (int i = 0; i < strArr.length; i++) {
			iPos = Integer.parseInt(strArr[i]);
			sortArr[i] = theArr[iPos];
		}
	}

	/**
	 * 快速排序 int 实现
	 * 
	 * @param sortArr
	 *            int[]
	 * @param strArr
	 *            String[]
	 */
	public static void sortOnStrArr(int[] sortArr, String[] strArr) {
		if (sortArr == null || strArr == null) {
			return;
		}
		if (sortArr.length != strArr.length || sortArr.length == 0) {
			return;
		}
		// 需要判断strArr是否这种特殊的 String[]

		int iPos;
		int[] theArr = new int[sortArr.length];
		for (int i = 0; i < theArr.length; i++) {
			theArr[i] = sortArr[i];
		}

		for (int i = 0; i < strArr.length; i++) {
			iPos = Integer.parseInt(strArr[i]);
			sortArr[i] = theArr[iPos];
		}
	}

	/**
	 * * 快速排序 Double 实现
	 * 
	 * @param arr
	 *            double数组
	 * @param arrPlus
	 *            String数组
	 * @param isAsc
	 *            是否升序
	 */
	public static void sortBy(double[] arr, boolean isAsc) {
		if (arr == null) {
			return;
		}
		double mult = isAsc ? FSD_ASC : FSD_DESC;

		int left = 0;
		int right = arr.length - 1;
		partSortBy(arr, left, right, mult);
	}

	public static void partSortBy(double[] arr, int left, int right, double mult) {
		if (arr == null || (arr != null && arr.length < 1) || left >= right) {
			return;
		}

		int newLeft, newRight;
		double strMidd, dblTemp;

		newLeft = left;
		newRight = right;
		strMidd = arr[(newLeft + newRight) / 2];

		do {
			while (mult * arr[newLeft] < mult * strMidd) {
				newLeft++;
			}

			while (mult * arr[newRight] > mult * strMidd) {
				newRight--;
			}

			if (newLeft <= newRight) {
				dblTemp = arr[newLeft];
				arr[newLeft] = arr[newRight];
				arr[newRight] = dblTemp;
				newLeft++;
				newRight--;
			}
		} while (newLeft <= newRight);

		if (newRight > left) {
			partSortBy(arr, left, newRight, mult);
		}
		if (newLeft < right) {
			partSortBy(arr, newLeft, right, mult);
		}
	}

	/**
	 * 快速排序 String 实现
	 * 
	 * @param arr
	 *            arr
	 * @param arrPlus
	 *            arrPlus
	 * @param isAsc
	 *            isAsc
	 */
	public static void sortBy(String[] arr, boolean isAsc) {
		if (arr == null) {
			return;
		}
		double mult = isAsc ? FSD_ASC : FSD_DESC;
		int left = 0;
		int right = arr.length - 1;
		partSortBy(arr, left, right, mult);
	}

	public static void partSortBy(String[] arr, int left, int right, double mult) {
		if (arr == null || (arr != null && arr.length < 1) || left >= right) {
			return;
		}

		int newLeft, newRight;
		String strMidd, strTemp;

		newLeft = left;
		newRight = right;

		strMidd = arr[(newLeft + newRight) / 2];
		do {
			while (mult * arr[newLeft].compareTo(strMidd) < 0) {
				newLeft++;
			}
			while (mult * arr[newRight].compareTo(strMidd) > 0) {
				newRight--;
			}
			if (newLeft <= newRight) {
				strTemp = arr[newLeft];
				arr[newLeft] = arr[newRight];
				arr[newRight] = strTemp;
				newLeft++;
				newRight--;
			}
		} while (newLeft <= newRight);

		if (newRight > left) {
			partSortBy(arr, left, newRight, mult);
		}
		if (newLeft < right) {
			partSortBy(arr, newLeft, right, mult);
		}
	}

	public static void main(String[] args) {
		double[] d = new double[4];
		d[0] = 2;
		d[1] = 1;
		d[2] = 5;
		d[3] = 4;
		String[] s = new String[4];
		s[1] = "ia2542";
		s[0] = "ie6e";
		s[2] = "utte5";
		s[3] = "z4e";

		SortUtil.sortBy(s, true);
		System.out.println(s[0]);
		System.out.println(s[1]);
		System.out.println(s[2]);
		System.out.println(s[3]);
		System.out.println("========");
		System.out.println(d[0]);
		System.out.println(d[1]);
		System.out.println(d[2]);
		System.out.println(d[3]);
		
		System.out.println("b".compareTo("c"));
	}
}
