<template>
  <div class="mx-auto container">
    <b-card header="Joined Households" header-tag="joined">
      <div v-if="user_households">
        <b-table responsive :items="user_households" :fields="user_households_fields">
          <template slot="id" slot-scope="data">
            <router-link :to="{ name: 'Household', params: { id: data.value }}">
              <b-button variant="primary">Check</b-button>
            </router-link>
          </template>
        </b-table>
      </div>
      <div v-else>
        You didn't joined any households yet.
      </div>
    </b-card>
    <b-card header="All households">
      <div v-if="households">
        <b-table responsive :items="households" :fields="all_household_fields">
          <template slot="id" slot-scope="data">
            <b-button href="#" variant="primary" v-if="joined(data.value.id)">Join</b-button>
            <b-button href="#" variant="success" disabled v-else>Joined</b-button>
          </template>
        </b-table>
      </div>
      <div v-else>
        No available households.
      </div>
    </b-card>
  </div>
</template>

<script>
  import services from "@/rms-service"

  export default {
    name: "Households",
    data() {
      return {
        user_households: null,
        households: null,
        all_household_fields: ["street", "buildingNumber", "zipCode", "state", {
          key: "id",
          label: "Join",
        }],
        user_households_fields: ["street", "buildingNumber", "zipCode", "state", {
          key: "id",
          label: "Check",
        }],
      };
    },

    created() {
      this.getData();
    },

    computed: {},

    methods: {
      async getData() {
        this.households = await services.getHouseholds();
        this.user_households = await services.getUserHouseholds(this.$store.state.user.id);
      },
      joined(hid) {
        if (this.user_households != null) {
          return this.user_households.find(h => h.id != null && h.id === hid)
        }
      }
    }
  }
</script>

<style scoped>

</style>
