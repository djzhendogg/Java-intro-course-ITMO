public class Sum {
    public static void main(String[] args) {
        int sum = 0;
        for (String arg: args) {
            arg += " ";
            int pointer = 0;
            boolean previosSpace = true;
            for (int i = 0; i < arg.length(); i++) {
                if (Character.isWhitespace(arg.charAt(i))) {
                    if (!previosSpace) {
                        sum += Integer.parseInt(arg.substring(pointer, i));
                    }
                    pointer = i;
                    previosSpace = true;
                } else if (previosSpace) {
                    pointer = i;
                    previosSpace = false;
                }
            }
        }
        System.out.println(sum);
    }
}