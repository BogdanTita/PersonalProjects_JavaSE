package RadixSort;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

public class RadixSort {

    ArrayList<String> elements = new ArrayList<>();
    private int numberOfChars;

    private ArrayList<Deque<String>> queues = new ArrayList<>(10);

    public RadixSort(int[] numbers){
        this.elements = generateStrings(numbers);

        this.numberOfChars = generateStrings(numbers).get(0).length();

        for(int i = 0; i < 10; i++){
            queues.add(i, new LinkedList<>());
        }
    }

    public void sort(){
        for(int i = this.numberOfChars - 1; i >= 0; i--){
            for(int j = 0; j < this.elements.size(); j++){
                int character = Character.getNumericValue(this.elements.get(j).charAt(i));

                queues.get(character).offer(elements.get(j));
            }

            int index = 0;
            for(int k = 9; k >= 0; k--){  //aici faci modificarea pt descrescator
                while(!queues.get(k).isEmpty()){
                    this.elements.set(index, queues.get(k).poll());
                    index++;
                }
            }
        }
    }

    public ArrayList<String> generateStrings(int[] numbers){
        ArrayList<String> result = new ArrayList<>();

        int maxNumber = 0;
        for(int i = 0; i < numbers.length; i++){
            result.add(String.valueOf(numbers[i]));
            if(result.get(i).length() > maxNumber){
                maxNumber = result.get(i).length();
            }
        }

        for(int i = 0; i < result.size(); i++){
            while(result.get(i).length() < maxNumber){
                result.set(i, ("0" + result.get(i)));
            }
        }

        return result;
    }

    public void print(){
        for(String el : this.elements){
            System.out.print(Integer.parseInt(el) + ", ");  // Integer.parseInt(el) + ", "
        }
    }


    public static void main(String[] args) {
        int[] numbers = {1000, 4, 25, 319, 88, 51, 3430, 8471, 701, 1, 2989, 657, 713};
        RadixSort radix = new RadixSort(numbers);
        radix.sort();
        radix.print();
    }

}
