package ru.tbank.hse.fd.streamPractise.service;

import ru.tbank.hse.fd.streamPractise.model.Car;
import ru.tbank.hse.fd.streamPractise.model.CarInfo;
import ru.tbank.hse.fd.streamPractise.model.Owner;
import ru.tbank.hse.fd.streamPractise.utils.Condition;

import java.util.List;
import java.util.stream.Collectors;

public class CarService {

    public List<String> getConditions(List<Car> cars) {
        return car.stream()
                .map(car -> car.getCondition().getText())
                .collect(Collectors.toList());
    }

    public List<Car> getNewCars(List<Car> cars) {
        return car.stream()
                .filter(car -> car.getCondition() == Condition.NEW)
                .collect(Collectors.toList());
    }

    public long countCarsOwners(List<Car> cars) {
        return car.stream()
                .filter(car -> car.getOwners().size() > 2)
                .count();
    }

    public List<Car> incrementCarAge(List<Car> cars) {
        return car.stream()
                .peek(car -> car.getAge(car.getAge() + 1))
                .collect(Collectors.toList());
    }
    public Car getOldestCar(List<Car> cars) {
        return car.stream()
                .max((car1, car2) -> Integer.compare(car1.getAge(), car2.getAge()))
                .orElse(null);
    }
    public List<String> getOwnersCarsNames(List<Car> cars) {
        return car.stream()
                .flatMap(car -> car.getOwners().stream())
                .map(Owner::getName)
                .distinct()
                .collect(Collectors.toList());
    }
    public List<CarInfo> mapToCarInfo(List<Car> cars) {
        return car.stream()
                .map(car -> new CarInfo(car.getName(), car.getAge(), car.getOwners().size()))
                .collect(Collectors.toList());
    }
    public List<Car> getTwoBrokenCar(List<Car> cars) {
        return car.stream()
                .filter(car -> car.getCondition() == Condition.BROKEN)
                .limit(2)
                .collect(Collectors.toList());
    }
    public List<Car> getSortedCarsByAge(List<Car> cars) {
        return car.stream()
                .sorted((car1, car2) -> Integer.compare(car1.getAge(), car2.getAge()))
                .collect(Collectors.toList());
    }
    public double getAvgCarsAge(List<Car> cars) {
        return car.stream()
                .mapToInt(Car::getAge)
                .average()
                .orElse(0);
    }
    public Boolean checkBrokenCarsAge(List<Car> cars) {
        return car.stream()
                .filter(car -> car.getCondition() == Condition.BROKEN)
                .allMatch(car -> car.getAge() > 10);
    }
    public Boolean checkCarOwnerName(List<Car> cars) {
        return car.stream()
                .filter(car -> car.getCondition() == Condition.USED)
                .flatMap(car -> car.getOwners().stream())
                .anyMatch(owner -> "Adam".equals(owner.getName()));
    }
    public Owner getAnyOwner(List<Car> cars) {
        return car.stream()
                .flatMap(car -> car.getOwners().stream())
                .filter(owner -> owner.getAge() > 36)
                .findAny()
                .orElse(null);
    }
}
