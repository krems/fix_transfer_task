package edu.mipt.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.util.UUID;

@XmlRootElement
public class Account {
    public static final Account EMPTY = new Account(UUID.randomUUID());
    @XmlElement
    public final UUID id;
    @XmlElement
    private BigDecimal balance = new BigDecimal(0);

    public Account(final UUID id) {
        this.id = id;
    }
    public Account() {
        id = EMPTY.id;
    }

    public void topup(final BigDecimal amount) {
        if (amount.signum() != 1) {
            throw new IllegalArgumentException("Topup amount must be positive");
        }
        balance = balance.add(amount);
    }

    public void withdraw(final BigDecimal amount) {
        if (amount.signum() != 1) {
            throw new IllegalArgumentException("Withdraw amount must be positive");
        }
        if (balance.subtract(amount).signum() == -1) {
            throw new IllegalArgumentException("Can't withdraw more than balance");
        }
        balance = balance.subtract(amount);
    }

    @Override
    public String toString() {
        return "Account{" +
            "id=" + id +
            ", balance=" + balance +
            '}';
    }
}
