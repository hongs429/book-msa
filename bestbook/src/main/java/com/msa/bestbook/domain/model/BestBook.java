package com.msa.bestbook.domain.model;


import com.msa.bestbook.domain.vo.Item;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "best_book")
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BestBook {
    @Id
    private String id;

    @Setter
    private Item item;

    @Setter
    private long rentCount;

    public static BestBook registerBestBook(Item item) {
        UUID uuid = UUID.randomUUID();
        var bestBook = new BestBook();
        bestBook.id = uuid.toString();
        bestBook.item = item;
        bestBook.rentCount = 1;
        return bestBook;
    }

    public Long increaseBestBookCount() {
        this.rentCount = this.getRentCount() + 1;
        return this.rentCount;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof BestBook)) return false;

        BestBook bestBook = (BestBook) obj;
        return id != null && id.equals(bestBook.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
