/**
 * Autogenerated by Thrift Compiler (0.14.2)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.14.2)", date = "2021-08-04")
public class Like implements org.apache.thrift.TBase<Like, Like._Fields>, java.io.Serializable, Cloneable, Comparable<Like> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("Like");

  private static final org.apache.thrift.protocol.TField ID_FIELD_DESC = new org.apache.thrift.protocol.TField("id", org.apache.thrift.protocol.TType.I32, (short)1);
  private static final org.apache.thrift.protocol.TField SONGID_FIELD_DESC = new org.apache.thrift.protocol.TField("Songid", org.apache.thrift.protocol.TType.I32, (short)2);
  private static final org.apache.thrift.protocol.TField NUM_LIKE_FIELD_DESC = new org.apache.thrift.protocol.TField("numLike", org.apache.thrift.protocol.TType.I32, (short)3);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new LikeStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new LikeTupleSchemeFactory();

  public int id; // required
  public int Songid; // required
  public int numLike; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    ID((short)1, "id"),
    SONGID((short)2, "Songid"),
    NUM_LIKE((short)3, "numLike");

    private static final java.util.Map<java.lang.String, _Fields> byName = new java.util.HashMap<java.lang.String, _Fields>();

    static {
      for (_Fields field : java.util.EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    @org.apache.thrift.annotation.Nullable
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // ID
          return ID;
        case 2: // SONGID
          return SONGID;
        case 3: // NUM_LIKE
          return NUM_LIKE;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new java.lang.IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    @org.apache.thrift.annotation.Nullable
    public static _Fields findByName(java.lang.String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final java.lang.String _fieldName;

    _Fields(short thriftId, java.lang.String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public java.lang.String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __ID_ISSET_ID = 0;
  private static final int __SONGID_ISSET_ID = 1;
  private static final int __NUMLIKE_ISSET_ID = 2;
  private byte __isset_bitfield = 0;
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.ID, new org.apache.thrift.meta_data.FieldMetaData("id", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32        , "int")));
    tmpMap.put(_Fields.SONGID, new org.apache.thrift.meta_data.FieldMetaData("Songid", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32        , "int")));
    tmpMap.put(_Fields.NUM_LIKE, new org.apache.thrift.meta_data.FieldMetaData("numLike", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32        , "int")));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(Like.class, metaDataMap);
  }

  public Like() {
  }

  public Like(
    int id,
    int Songid,
    int numLike)
  {
    this();
    this.id = id;
    setIdIsSet(true);
    this.Songid = Songid;
    setSongidIsSet(true);
    this.numLike = numLike;
    setNumLikeIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public Like(Like other) {
    __isset_bitfield = other.__isset_bitfield;
    this.id = other.id;
    this.Songid = other.Songid;
    this.numLike = other.numLike;
  }

  public Like deepCopy() {
    return new Like(this);
  }

  @Override
  public void clear() {
    setIdIsSet(false);
    this.id = 0;
    setSongidIsSet(false);
    this.Songid = 0;
    setNumLikeIsSet(false);
    this.numLike = 0;
  }

  public int getId() {
    return this.id;
  }

  public Like setId(int id) {
    this.id = id;
    setIdIsSet(true);
    return this;
  }

  public void unsetId() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __ID_ISSET_ID);
  }

  /** Returns true if field id is set (has been assigned a value) and false otherwise */
  public boolean isSetId() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __ID_ISSET_ID);
  }

  public void setIdIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __ID_ISSET_ID, value);
  }

  public int getSongid() {
    return this.Songid;
  }

  public Like setSongid(int Songid) {
    this.Songid = Songid;
    setSongidIsSet(true);
    return this;
  }

  public void unsetSongid() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __SONGID_ISSET_ID);
  }

  /** Returns true if field Songid is set (has been assigned a value) and false otherwise */
  public boolean isSetSongid() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __SONGID_ISSET_ID);
  }

  public void setSongidIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __SONGID_ISSET_ID, value);
  }

  public int getNumLike() {
    return this.numLike;
  }

  public Like setNumLike(int numLike) {
    this.numLike = numLike;
    setNumLikeIsSet(true);
    return this;
  }

  public void unsetNumLike() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __NUMLIKE_ISSET_ID);
  }

  /** Returns true if field numLike is set (has been assigned a value) and false otherwise */
  public boolean isSetNumLike() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __NUMLIKE_ISSET_ID);
  }

  public void setNumLikeIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __NUMLIKE_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, @org.apache.thrift.annotation.Nullable java.lang.Object value) {
    switch (field) {
    case ID:
      if (value == null) {
        unsetId();
      } else {
        setId((java.lang.Integer)value);
      }
      break;

    case SONGID:
      if (value == null) {
        unsetSongid();
      } else {
        setSongid((java.lang.Integer)value);
      }
      break;

    case NUM_LIKE:
      if (value == null) {
        unsetNumLike();
      } else {
        setNumLike((java.lang.Integer)value);
      }
      break;

    }
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case ID:
      return getId();

    case SONGID:
      return getSongid();

    case NUM_LIKE:
      return getNumLike();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case ID:
      return isSetId();
    case SONGID:
      return isSetSongid();
    case NUM_LIKE:
      return isSetNumLike();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that instanceof Like)
      return this.equals((Like)that);
    return false;
  }

  public boolean equals(Like that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_id = true;
    boolean that_present_id = true;
    if (this_present_id || that_present_id) {
      if (!(this_present_id && that_present_id))
        return false;
      if (this.id != that.id)
        return false;
    }

    boolean this_present_Songid = true;
    boolean that_present_Songid = true;
    if (this_present_Songid || that_present_Songid) {
      if (!(this_present_Songid && that_present_Songid))
        return false;
      if (this.Songid != that.Songid)
        return false;
    }

    boolean this_present_numLike = true;
    boolean that_present_numLike = true;
    if (this_present_numLike || that_present_numLike) {
      if (!(this_present_numLike && that_present_numLike))
        return false;
      if (this.numLike != that.numLike)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + id;

    hashCode = hashCode * 8191 + Songid;

    hashCode = hashCode * 8191 + numLike;

    return hashCode;
  }

  @Override
  public int compareTo(Like other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.compare(isSetId(), other.isSetId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.id, other.id);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.compare(isSetSongid(), other.isSetSongid());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetSongid()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.Songid, other.Songid);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.compare(isSetNumLike(), other.isSetNumLike());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetNumLike()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.numLike, other.numLike);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  @org.apache.thrift.annotation.Nullable
  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    scheme(iprot).read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    scheme(oprot).write(oprot, this);
  }

  @Override
  public java.lang.String toString() {
    java.lang.StringBuilder sb = new java.lang.StringBuilder("Like(");
    boolean first = true;

    sb.append("id:");
    sb.append(this.id);
    first = false;
    if (!first) sb.append(", ");
    sb.append("Songid:");
    sb.append(this.Songid);
    first = false;
    if (!first) sb.append(", ");
    sb.append("numLike:");
    sb.append(this.numLike);
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, java.lang.ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class LikeStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public LikeStandardScheme getScheme() {
      return new LikeStandardScheme();
    }
  }

  private static class LikeStandardScheme extends org.apache.thrift.scheme.StandardScheme<Like> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, Like struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.id = iprot.readI32();
              struct.setIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // SONGID
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.Songid = iprot.readI32();
              struct.setSongidIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // NUM_LIKE
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.numLike = iprot.readI32();
              struct.setNumLikeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, Like struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(ID_FIELD_DESC);
      oprot.writeI32(struct.id);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(SONGID_FIELD_DESC);
      oprot.writeI32(struct.Songid);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(NUM_LIKE_FIELD_DESC);
      oprot.writeI32(struct.numLike);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class LikeTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public LikeTupleScheme getScheme() {
      return new LikeTupleScheme();
    }
  }

  private static class LikeTupleScheme extends org.apache.thrift.scheme.TupleScheme<Like> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, Like struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetId()) {
        optionals.set(0);
      }
      if (struct.isSetSongid()) {
        optionals.set(1);
      }
      if (struct.isSetNumLike()) {
        optionals.set(2);
      }
      oprot.writeBitSet(optionals, 3);
      if (struct.isSetId()) {
        oprot.writeI32(struct.id);
      }
      if (struct.isSetSongid()) {
        oprot.writeI32(struct.Songid);
      }
      if (struct.isSetNumLike()) {
        oprot.writeI32(struct.numLike);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, Like struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(3);
      if (incoming.get(0)) {
        struct.id = iprot.readI32();
        struct.setIdIsSet(true);
      }
      if (incoming.get(1)) {
        struct.Songid = iprot.readI32();
        struct.setSongidIsSet(true);
      }
      if (incoming.get(2)) {
        struct.numLike = iprot.readI32();
        struct.setNumLikeIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}
