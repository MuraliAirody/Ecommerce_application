import { Button, Grid, Box, TextField } from "@mui/material";
import React, { useState } from "react";
import AddressCard from "../AddressCard/AddressCard";
import { useDispatch, useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import { createOrder } from "../../../redux/customer/order/action";

function DeliveryAddressForm() {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const jwt = localStorage.getItem("jwt");
  const auth = useSelector((store) => store.auth);
  const [selectedAddress, setSelectedAddress] = useState(null);

  function handleSubmit(e) {
    e.preventDefault();
    const data = new FormData(e.currentTarget);

    const address = {
      firstName: data.get("firstName"),
      lastName: data.get("lastName"),
      streetAddress: data.get("address"),
      city: data.get("city"),
      state: data.get("state"),
      zipCode: data.get("zip"),
      mobile: data.get("phoneNumber"),
    };
    dispatch(createOrder({address,jwt,navigate}));
    console.log(address);
  }
  const handleCreateOrder = (item) => {
    console.log("item: ",item)
    dispatch(createOrder({address:item, jwt, navigate }));
    // handleNext();
  };
  return (
    <div>
      <Grid container spacing={4}>
        <Grid
          xs={12}
          lg={5}
          item
          className="border rounded-md shadow-md h-[30.5rem] overflow-scroll"
        >
          <Box className="border rounded-md shadow-md h-[30.5rem] overflow-y-scroll ">
            {auth.user?.addresses.slice(-3).map((item,index) => (
              <div
                key={index}
                onClick={() => setSelectedAddress(item)}
                className="p-5 py-7 border-b cursor-pointer"
              >
                {" "}
                <AddressCard address={item} />
                {selectedAddress?.id === item.id && (
                  <Button
                    sx={{ mt: 2 }}
                    size="large"
                    variant="contained"
                    color="primary"
                    onClick={() => handleCreateOrder(item)}
                  >
                    Deliver Here
                  </Button>
                )}
              </div>
            ))}
          </Box>
        </Grid>
        <Grid item xs={12} lg={7}>
          <Box className="border rounded-md shadow-md p-5">
            <form onSubmit={handleSubmit}>
              <Grid container spacing={3}>
                <Grid item xs={12} sm={6}>
                  <TextField
                    id="firstName"
                    name="firstName"
                    label="First Name"
                    fullWidth
                    autoComplete="given-name"
                    //   value={}
                    required
                  />
                </Grid>
                <Grid item xs={12} sm={6}>
                  <TextField
                    id="lastName"
                    name="lastName"
                    label="Last Name"
                    fullWidth
                    autoComplete="given-name"
                    //   value={}
                    required
                  />
                </Grid>
                <Grid item xs={12}>
                  <TextField
                    id="address"
                    name="address"
                    label="Address"
                    fullWidth
                    autoComplete="given-name"
                    //   value={}
                    required
                    multiline
                    rows={5}
                  />
                </Grid>
                <Grid item xs={12} sm={6}>
                  <TextField
                    id="city"
                    name="city"
                    label="City"
                    fullWidth
                    autoComplete="given-name"
                    //   value={}
                    required
                  />
                </Grid>
                <Grid item xs={12} sm={6}>
                  <TextField
                    id="state"
                    name="state"
                    label="State/Region"
                    fullWidth
                    autoComplete="given-name"
                    //   value={}
                    required
                  />
                </Grid>
                <Grid item xs={12} sm={6}>
                  <TextField
                    id="zip"
                    name="zip"
                    label="zip / Postal code"
                    fullWidth
                    //   value={}
                    required
                    autoComplete="shipping postal-code"
                  />
                </Grid>
                <Grid item xs={12} sm={6}>
                  <TextField
                    id="phoneNumber"
                    name="phoneNumber"
                    label="Phone Number"
                    fullWidth
                    autoComplete="given-name"
                    //   value={}
                    required
                  />
                </Grid>
                <Grid item xs={12}>
                  <Button
                    className="w-full"
                    sx={{ bgcolor: "#32CD32", color: "black" }}
                    variant="contained"
                    color="success"
                    type="submit"
                  >
                    Deliver Here
                  </Button>{" "}
                </Grid>
              </Grid>
            </form>
          </Box>
        </Grid>
      </Grid>
    </div>
  );
}

export default DeliveryAddressForm;
