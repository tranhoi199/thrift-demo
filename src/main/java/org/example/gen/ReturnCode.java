/**
 * Autogenerated by Thrift Compiler (0.14.2)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package org.example.gen;


@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.14.2)", date = "2021-08-10")
public enum ReturnCode implements org.apache.thrift.TEnum {
  SUCCESS(200),
  INVALID(406),
  INTERNAL_SERVER_ERROR(500);

  private final int value;

  private ReturnCode(int value) {
    this.value = value;
  }

  /**
   * Get the integer value of this enum value, as defined in the Thrift IDL.
   */
  public int getValue() {
    return value;
  }

  /**
   * Find a the enum type by its integer value, as defined in the Thrift IDL.
   * @return null if the value is not found.
   */
  @org.apache.thrift.annotation.Nullable
  public static ReturnCode findByValue(int value) { 
    switch (value) {
      case 200:
        return SUCCESS;
      case 406:
        return INVALID;
      case 500:
        return INTERNAL_SERVER_ERROR;
      default:
        return null;
    }
  }
}
