import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HelloRoman {
	
	public static void main(String[] args) throws IOException {
    	String rule = "set size [1-9][0-9]*";
    	String regex = "M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})";
    	String input = "";
    	int size = 1;
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	while(true) {
    		input = br.readLine();
        	
        	if("exit".equals(input)) {
        		br.close();
        		System.exit(0);
        	}else if(input.matches(rule)) {
        		
        		String sizeStr = input.substring(9, input.length());
        		
        		if(sizeStr != null || !sizeStr.isEmpty()) {
        			int inputSize = Integer.parseInt(sizeStr);
            		size = inputSize==0? 1 : inputSize;
        		}
        		
        	}else if(input.matches(regex)) {
        		if(input != null && !input.isEmpty()) {
        			getNumber(input, size);
        		}else {
        			getNumber("E", size);
        		}
        	}else {
        		getNumber("E", size);
        	}
    	}
    }
	
	static String repeat (String str, int time) {
        String t = "";
        for (int i=0; i<time; ++i) {
            t += str;
        }
        return t;
    }
	
	static String joinStr(String[] arr, String s) {
		int offset = arr.length;
		StringBuilder sb = new StringBuilder(arr.length*3);
		
		for( int i = 0; i < offset; i++ )
		{
			
			if(i==offset-1) {
				sb.append(arr[i]);
			}else {
				sb.append(arr[i]).append(s);
			}
		}
		 
		return sb.toString();
	}
	
	static void getNumber(String input ,int size) {
		int[] number = new int[666];
		number['I'] = 1;
		number['V'] = 5;
		number['X'] = 10;
		number['L'] = 50;
		number['C'] = 100;
		number['D'] = 500;
		number['M'] = 1000;

		char[] target = input.toCharArray();
		
        int sum = number[target[0]];
        for(int i=0; i<target.length-1; i++){
            if(number[target[i]] >= number[target[i+1]]){
                sum += number[target[i+1]];
            }
            else{
                sum = sum + number[target[i+1]] - 2*number[target[i]];
            }
        }
        
        if("E".equals(input)) {
        	led("E", size);
        }else {
        	led(String.valueOf(sum), size);
        }
        
	}
	
	static void led(String number, int size) {
    	String[] result = new String[size*2+3];
    	
    	for(int i=0; i < size*2+3; ++i) {
    		result[i] = "";
    	}
    	
        for(int i=0; i < number.length(); ++i) {
        	String ch = String.valueOf(number.charAt(i));

        	if((number.length()-i)%3 == 0) {
        		for(int j = 0; j < result.length-1;++j) {
					result[j] += " ";
				}

				result[size*2+2] += ",";
			}

        	result[0] += " " + repeat( "02356789E".contains(ch) ? "-" : " ", size) + " ";
        	
        	for(int j=1; j<= size; ++j) {
        		result[j] += ("045689E".contains(ch) ? "|" : " ") + repeat(" ", size) 
        					+ ("01234789".contains(ch) ? "|" : " ");
        	}
        	
        	result[size+1] += " " + repeat("2345689E".contains(ch) ? "-" : " ", size) + " ";
        	
        	for(int j=size+2; j< size*2+2; ++j) {
        		result[j] += ("0268E".contains(ch) ? "|" : " ") + repeat(" ", size)
        					+ ("013456789".contains(ch) ? "|" : " ");
        	}
        	
        	result[size*2+2] += " " + repeat("0235689E".contains(ch) ? "-" : " ", size) + " ";
        }
        System.out.println(joinStr(result, "\n"));
	}
}
