package com.netcracker.entities;

import org.jetbrains.annotations.NotNull;
import org.joda.time.LocalDate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Represents the Digital TV contract.
 *
 * <p>Please see the {@link Contract} class for true identity
 *
 * @author Vlad Kotov
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class DigitalTvContract extends Contract {
  @XmlElement
  private @NotNull String channelPack;

  /**
   * Creates a Digital TV Contract.
   *
   * @param id             the contract id
   * @param startDate      the date of the beginning of the contract
   * @param endDate        the end date of the contract
   * @param contractNumber the number of the contract
   * @param person         person entity
   * @param channelPack    available channels in the contract
   */
  public DigitalTvContract(int id, @NotNull LocalDate startDate, @NotNull LocalDate endDate,
                           int contractNumber,
                           @NotNull Person person, @NotNull String channelPack) {
    super(id, startDate, endDate, contractNumber, person);
    this.channelPack = channelPack;
  }
  public DigitalTvContract(){

  }

  public @NotNull String getChannelPack() {
    return channelPack;
  }

  public void setChannelPack(@NotNull String channelPack) {
    this.channelPack = channelPack;
  }
}
