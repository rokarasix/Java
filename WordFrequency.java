

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;


public class WordFrequency {

    private static int N;


    public void getMostFreqAndLeastFreqWords(File file, int num) throws Exception {

        N = num;
        BufferedReader br = new BufferedReader(new FileReader(file));
        String data = "", st;
        while ((st = br.readLine()) != null) {
            data = data + st;
        }
        Map<String, Integer> map = splitIntoWordsAndStoreInMap(data);
        Map<String, Integer> sortedMap = entriesSortedByValuesAndKey(map);
        LinkedHashMap<Integer, List> bucketListDup1 = mapIntoBucketList(sortedMap);
        LinkedHashMap<Integer, List> bucketListDup2 = mapIntoBucketList(sortedMap);
        printMostFrequentWords(bucketListDup1, N);
        printLeastFrequentWords(bucketListDup2, N);
    }


    private Map<String, Integer> splitIntoWordsAndStoreInMap(String data) {

        String input = data.replaceAll("[^a-zA-Z0-9\\s\\.]", " ").trim();
        Map<String, Integer> map = new LinkedHashMap<>();
        String words[] = input.trim().split("[.\\s+]");
        for (String word : words) {
            if (!word.isEmpty() || word.length() >= 1) {
                if (!map.containsKey(word.toLowerCase())) {
                    map.put(word.toLowerCase(), 1);
                }
                else {
                    int count = map.get(word.toLowerCase());
                    map.put(word.toLowerCase(), ++count);
                }
            }
        }
        return map;
    }


    private Map<String, Integer> entriesSortedByValuesAndKey(Map<String, Integer> map) {

        SortedSet<Map.Entry<String, Integer>> sortedEntries = new TreeSet<>(
                new Comparator<Map.Entry<String, Integer>>() {

                    @Override public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {

                        int result = o1.getValue().compareTo(o2.getValue());
                        if (result == 0) {
                            return o2.getKey().compareTo(o1.getKey());
                        }
                        else {
                            return result > 0 ? -1 : 1; //decreasing
                        }
                    }
                }
        );
        sortedEntries.addAll(map.entrySet());
        LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : sortedEntries) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }


    private LinkedHashMap<Integer, List> mapIntoBucketList(Map<String, Integer> sortedMap) {

        int freqNo = 0;
        LinkedHashMap<Integer, List> bucketList = new LinkedHashMap<>();
        List<String> list = new ArrayList<>();
        for (Map.Entry<String, Integer> i : sortedMap.entrySet()) {
            if (freqNo == 0 || freqNo == i.getValue()) {
                list.add(i.getKey());
                freqNo = i.getValue();
            }
            else {
                bucketList.put(freqNo, list);
                list = new ArrayList<>();
                list.add(i.getKey());
                freqNo = i.getValue();
            }
            bucketList.put(freqNo, list);
        }
        return bucketList;
    }


    private void printMostFrequentWords(LinkedHashMap<Integer, List> bucketListDup1, int N) {

        Map<String, Integer> nFreqSeries = new LinkedHashMap<>();
        int max = findMax(bucketListDup1);
        System.out.println("Most frequent");
        while (nFreqSeries.size() < N) {
            List<String> list = bucketListDup1.get(max);
            if (list.size() > (N - nFreqSeries.size())) {
                nFreqSeries.putAll(generateRandomNumber(list, max, N - nFreqSeries.size()));
            }
            else {
                for (String each : list) {
                    nFreqSeries.put(each, max);
                }
                bucketListDup1.remove(max);
                max = findMax(bucketListDup1);
            }
        }
        printInWordCount(nFreqSeries);
        System.out.println();
    }


    private void printLeastFrequentWords(LinkedHashMap<Integer, List> bucketListDup2, int N) {

        Map<String, Integer> nFreqSeries = new LinkedHashMap<>();
        System.out.println("Least frequent");
        int min = findMin(bucketListDup2);
        while (nFreqSeries.size() < N) {
            List<String> list = bucketListDup2.get(min);
            if (list.size() > (N - nFreqSeries.size())) {
                nFreqSeries.putAll(generateRandomNumber(list, min, N - nFreqSeries.size()));
            }
            else {
                for (String each : list) {
                    nFreqSeries.put(each, min);
                }
                bucketListDup2.remove(min);
                min = findMin(bucketListDup2);
            }
        }
        printInWordCount(nFreqSeries);
        System.out.println();
    }


    private Map<String, Integer> generateRandomNumber(List<String> stringList, int n, int range) {

        Map<String, Integer> randomSeries = new LinkedHashMap<>();
        Random rand = new Random();
        for (int i = 0; i < range; i++) {
            int randomIndex = rand.nextInt(stringList.size());
            String randomElement = stringList.get(randomIndex);
            randomSeries.put(randomElement, n);
            stringList.remove(randomIndex);
        }
        return randomSeries;
    }


    private int findMin(Map<Integer, List> bucketList) {

        int min = Integer.MAX_VALUE;
        for (Map.Entry<Integer, List> i : bucketList.entrySet()) {
            if (min > i.getKey()) {
                min = i.getKey();
            }
        }
        return min;
    }


    private int findMax(Map<Integer, List> bucketList) {

        int max = Integer.MIN_VALUE;
        for (Map.Entry<Integer, List> i : bucketList.entrySet()) {
            if (max < i.getKey()) {
                max = i.getKey();
            }
        }
        return max;
    }


    private void printInWordCount(Map<String, Integer> map) {

        Map.Entry<String, Integer> lastElement = null;
        for (Map.Entry entry : map.entrySet()) {
            lastElement = entry;
        }
        for (Map.Entry entry : map.entrySet()) {
            if (entry != lastElement) {
                System.out.print("(" + entry.getKey() + ":" + entry.getValue() + "), ");
            }
            else {
                System.out.print("(" + entry.getKey() + ":" + entry.getValue() + ")");
            }
        }
    }
}

