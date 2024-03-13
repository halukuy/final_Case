package com.example.halukuyumsal.final_case.general;

import lombok.Getter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


@Getter
public abstract class BaseEntityService<E extends BaseEntity, R extends JpaRepository<E, Long>> {

  private final R repository;

  protected BaseEntityService(R repository) {
    this.repository = repository;
  }

  public E save(E entity) {

    BaseAdditionalFields baseAdditionalFields = entity.getBaseAdditionalFields();
    if (baseAdditionalFields == null) {
      baseAdditionalFields = new BaseAdditionalFields();
    }


    //entity.getBaseAdditionalFields().setUpdaterId();
    entity.setBaseAdditionalFields(baseAdditionalFields);

    entity = repository.save(entity);
    return entity;
  }

  public List<E> findAll() {
    return repository.findAll();
  }

  public E findByIdWithControl(Long id) {
    Optional<E> optionalE = repository.findById(id);
    E entity;
    if (optionalE.isPresent()) {
      entity = optionalE.get();
    } else {
      throw new RuntimeException();
      //throw new ItemNotFoundException("Data bulunamadı!");
    }

    return entity;
  }

  public Optional<E> findById(Long id){
    return repository.findById(id);
  }

  public void delete(Long id) {
    repository.deleteById(id);
  }

  public void delete(E entity){
    repository.delete(entity);
  }

  public boolean existById(Long id){
    return repository.existsById(id);
  }

}
