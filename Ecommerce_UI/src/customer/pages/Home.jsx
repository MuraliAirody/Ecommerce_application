import React, { useState } from "react";
import MainCarousel from "../components/Home_Carousel/MainCarousel";
import HomeSectionsCarousel from "../components/Home_Sections_Carousel/HomeSectionsCarousel";

function Home() {

  return (
    <div>
      <div>
        <MainCarousel></MainCarousel>
      </div>
      <div className="space-y-10 py-20 flex flex-col justify-center px-5 lg:px-10">
        <HomeSectionsCarousel />
        <HomeSectionsCarousel />
        <HomeSectionsCarousel />
        <HomeSectionsCarousel />
        <HomeSectionsCarousel />
      </div>
    </div>
  );
}

export default Home;
