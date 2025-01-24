public class SumFloat {
    public static void main(String[] args) {
        float sum = 0;
        for (String arg: args) {
            int pointer = 0;
            boolean previosSpace = true;
            for (int i = 0; i < arg.length(); i++) {
                if (Character.isWhitespace(arg.charAt(i))) {
                    if (!previosSpace) {
                        sum += Float.parseFloat(arg.substring(pointer, i));
                    }
                    pointer = i;
                    previosSpace = true;
                } else if (previosSpace) {
                    pointer = i;
                    previosSpace = false;
                }
            }
			if (!previosSpace) {
                sum += Float.parseFloat(arg.substring(pointer, arg.length()));
            }
        }
        System.out.println(sum);
    }
}
