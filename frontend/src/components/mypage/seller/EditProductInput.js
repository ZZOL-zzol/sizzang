const EditProductInput = (props) => {
  return (
    <input
      className="bg-white border border-myprimary rounded-lg px-2 py-1 focus:outline-myprimary w-32 text-sm"
      value={props.value}
      onChange={props.onInputChange}
      name={props.data}
      placeholder={props.placeholder}
    />
  );
};

export default EditProductInput;
