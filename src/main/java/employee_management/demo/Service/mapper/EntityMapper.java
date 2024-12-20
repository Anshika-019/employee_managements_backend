package employee_management.demo.Service.mapper;

import java.util.ArrayList;
import java.util.List;

public interface EntityMapper <D,E>{
    E toEntity(D dto);
    D toDto(E entity);
    List<E> toEntity(List<D> dtoList);
    default List<D> toDto(List<E> entities){
        List<D> list=new ArrayList<>();
        for (E phase :  entities) {
            list.add(toDto(phase));
        }
        return list;
    }
}
