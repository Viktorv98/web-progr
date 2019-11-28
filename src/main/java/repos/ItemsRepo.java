package repos;

import com.example.sweater.models.Items;
import org.springframework.data.repository.CrudRepository;

public interface ItemsRepo extends CrudRepository<Items, Integer> {

}