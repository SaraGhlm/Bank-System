/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javabankproject.Data.Manager;

import java.util.Arrays;
import java.util.Random;
import javabankproject.Data.Branch;
import javabankproject.Data.City;

/**
 *
 * @author Novin Pendar
 */
public class CityManager {

    public static final CityManager manager = new CityManager();
    private int cityLength = 1;
    private City city[] = new City[cityLength];

    private int branchLength = 1;
    private Branch branch[] = new Branch[branchLength];

    private CityManager() {
    }

    public City[] getCity() {
        return city;
    }

    public Branch[] getBranch() {
        return branch;
    }

    public Branch getBranch(int i) {
        return branch[i];
    }

    public boolean addCity(String name) {
        boolean exist = true;

        for (int i = 0; i < cityLength - 1; i++) {
            if (city[i].getName().equalsIgnoreCase(name)) {
                exist = false;
            }
        }

        if (exist) {
            Random rand = new Random();

            int code = 10 + rand.nextInt(90);

            for (int i = 0; i < cityLength - 1; i++) {
                if (city[i].getCode() == code) {
                    code = 10 + rand.nextInt(90);
                    i = 0;
                }
            }

            City temp = new City(code, name);
            city[cityLength - 1] = temp;

            City cityTemp[] = Arrays.copyOf(city, cityLength + 1);
            city = cityTemp;

            cityLength++;
        }

        return exist;
    }

    public City findCity(String name) {
        City ct = null;

        for (int i = 0; i < cityLength - 1; i++) {
            if (city[i].getName().equalsIgnoreCase(name)) {
                ct = city[i];
            }
        }

        return ct;
    }

    public boolean addBranch(String branchName, City ct) {
        boolean exist = true;

        for (int i = 0; i < branchLength - 1; i++) {
            if (branch[i].getName().equalsIgnoreCase(branchName)) {
                exist = false;
            }
        }
        if (exist) {
            Random rand = new Random();

            int code = 100 + rand.nextInt(890);

            for (int i = 0; i < branchLength - 1; i++) {
                if (branch[i].getCode() == code) {
                    code = 100 + rand.nextInt(990);
                    i = 0;
                }
            }
            Branch temp = new Branch(code, branchName, ct);
            branch[branchLength - 1] = temp;

            Branch branchTemp[] = Arrays.copyOf(branch, branchLength + 1);
            branch = branchTemp;

            branchLength++;
        }
        return exist;
    }

    public Branch findBranch(String branchName, String cityName) {
        Branch br = null;

        for (int i = 0; i < branchLength - 1; i++) {
            if (branch[i].getName().equalsIgnoreCase(branchName)) {
                if (branch[i].getCity().getName().equals(cityName)) {
                    br = branch[i];
                }
            }
        }

        return br;
    }
}
