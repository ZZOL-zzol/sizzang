const Dropdown = (props) => {
  return (
    <select className="select select-bordered border-myprimary w-full font-normal text-base" onChange={props.onChangeEvent} value={props.value}>
      <option disabled selected className="text-outline" value='none'>
        회원 유형
      </option>
      <option value='ROLE_SELLER'>판매자</option>
      <option value='ROLE_CUSTOMER'>소비자</option>
    </select>
  );
};

export default Dropdown;