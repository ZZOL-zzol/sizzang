const Dropdown = () => {
  return (
    <select className="select select-bordered border-myprimary w-full font-normal text-base">
      <option disabled selected className="text-outline">
        회원 유형
      </option>
      <option>판매자</option>
      <option>소비자</option>
    </select>
  );
};

export default Dropdown;